package revolut.task.controller

import io.micronaut.context.env.Environment
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Test
import revolut.task.Application
import revolut.task.dto.AccountDto
import revolut.task.service.AccountService
import javax.inject.Inject

@MicronautTest(application = Application::class, environments = [Environment.TEST])
class AccountControllerTest {

    @Inject
    lateinit var server: EmbeddedServer

    @Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @Inject
    lateinit var accountService: AccountService

    @Test
    fun shouldGetAllAccounts() {
        val accounts = client.toBlocking().retrieve(HttpRequest.GET<Any>("/accounts"), Argument.of(List::class.java, AccountDto::class.java))
        assertEquals(6, accounts.size)
    }

    @Test
    fun shouldGetAccount() {
        val id = 1L
        val account = client.toBlocking().retrieve(HttpRequest.GET<Any>("/accounts/$id"), AccountDto::class.java)
        assertEquals(id, account.id)
    }

    @Test
    fun shouldReturnErrorIfAccountNotExists() {
        val id = 100L
        try {
            client.toBlocking().exchange(HttpRequest.GET<Any>("/accounts/$id"), AccountDto::class.java)
            fail<Any>()
        } catch (e: HttpClientResponseException) {
            assertEquals(HttpStatus.NOT_FOUND, e.response.status)
        }
    }

    @Test
    fun shouldCreateAccount() {
        val request = HttpRequest.POST("/accounts", AccountDto(null, "name", "USD", "10000"))
        val response = client.toBlocking().exchange<AccountDto, Any>(request)
        assertEquals(HttpStatus.CREATED, response.status)
        val locationParts = response.headers.get("location")?.split("accounts/")
        locationParts?.let {
            accountService.deleteAccount(it[1].toLong())
        }
    }
}
