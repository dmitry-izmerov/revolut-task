package revolut.task.controller

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import revolut.task.dto.AccountDto
import revolut.task.mapper.AccountMapper
import revolut.task.service.AccountService
import java.net.URI
import javax.inject.Inject

@Controller("/accounts")
class AccountController(@Inject private val accountService: AccountService) {

    @Get
    fun getAllAccounts(): Collection<AccountDto> {
        val accounts = accountService.getAccounts()
        return AccountMapper.INSTANCE.toDto(accounts)
    }

    @Get("/{id}")
    fun getAccount(@PathVariable id: Long): AccountDto {
        val account = accountService.getAccount(id)
        return AccountMapper.INSTANCE.toDto(account)
    }

    @Post
    fun createAccount(@Body dto: AccountDto): HttpResponse<Any> {
        val model = AccountMapper.INSTANCE.toModel(dto)
        val id = accountService.createAccount(model)
        return HttpResponse.created(URI.create("/accounts/$id"))
    }
}