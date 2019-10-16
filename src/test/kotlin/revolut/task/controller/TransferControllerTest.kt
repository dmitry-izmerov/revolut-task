package revolut.task.controller

import io.micronaut.context.env.Environment
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import revolut.task.Application
import revolut.task.dto.AccountDto
import revolut.task.dto.TransferRequestDto
import revolut.task.dto.TransferResponseDto
import java.math.BigDecimal
import java.util.stream.LongStream
import javax.inject.Inject
import kotlin.streams.toList

@MicronautTest(application = Application::class, environments = [Environment.TEST])
class TransferControllerTest {

    @Inject
    lateinit var server: EmbeddedServer

    @Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @Test
    fun shouldGetAllTransfers() {
        val uri = "/transfers?fromAccount=1&toAccount=2"
        val argument = Argument.of(List::class.java, TransferResponseDto::class.java)
        val transfers = client.toBlocking().retrieve(HttpRequest.GET<Any>(uri), argument)
        assertEquals(1, transfers.size)
    }

    @Test
    fun shouldGetTransfer() {
        val id = 1L
        val account = client.toBlocking().retrieve(HttpRequest.GET<Any>("/transfers/$id"), TransferResponseDto::class.java)
        assertEquals(id, account.id)
    }

    @Test
    fun shouldCreateTransfer() {
        val firstAccount = 5L
        val secondAccount = 6L
        val request = HttpRequest.POST("/transfers", TransferRequestDto(firstAccount, secondAccount, "RUB", "1000"))
        val response = client.toBlocking().exchange(request, TransferResponseDto::class.java)
        assertEquals(HttpStatus.OK, response.status)
        val backRequest = HttpRequest.POST("/transfers", TransferRequestDto(secondAccount, firstAccount, "RUB", "1000"))
        val backResponse = client.toBlocking().exchange(backRequest, TransferResponseDto::class.java)
        assertEquals(HttpStatus.OK, backResponse.status)
    }

    @Test
    fun checkTransferMoneyInParallel() {
        server.applicationContext.getBean(AccountController::class.java)

        val blockingClient = client.toBlocking()
        val totalTransferAmount = 1000
        val amount = 10
        val threadsNum = 3L
        val threads = LongStream.rangeClosed(1, threadsNum).toList()
            .map {
                Thread {
                    for (i in 1..(totalTransferAmount / amount)) {
                        val dto = TransferRequestDto(it, it + 1, "RUB", amount.toString())
                        val request = HttpRequest.POST("/transfers", dto)
                        blockingClient.exchange(request, TransferResponseDto::class.java)
                    }
                }
            }

        threads.forEach { it.start() }
        threads.forEach { it.join() }

        val account = client.toBlocking().retrieve(HttpRequest.GET<Any>("/accounts/${threadsNum + 1}"), AccountDto::class.java)
        assertEquals(BigDecimal("1000"), account.balance?.toBigDecimal())
    }
}
