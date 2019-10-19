package revolut.task.service

import revolut.task.exception.BadRequestException
import revolut.task.model.Account
import revolut.task.model.Transfer
import revolut.task.repository.AccountRepository
import java.math.BigDecimal
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountServiceImpl(@Inject private val repository: AccountRepository) : AccountService {

    override fun getAccounts(): List<Account> {
        return repository.findAll()
    }

    override fun getAccount(id: Long): Account {
        return repository.findById(id)
    }

    override fun createAccount(account: Account): Long {
        return repository.create(account)
    }

    override fun deleteAccount(id: Long) {
        repository.deleteById(id)
    }

    override fun transfer(transfer: Transfer) {
        validate(transfer)
        repository.transfer(transfer.fromAccount!!, transfer.toAccount!!, transfer.currency.toString(), transfer.amount!!)
    }

    private fun validate(transfer: Transfer) {
        if (transfer.amount!! < BigDecimal.ZERO || transfer.amount == BigDecimal.ZERO) {
            throw BadRequestException("Transfer amount should be positive number.")
        }

        if (transfer.fromAccount == transfer.toAccount) {
            throw BadRequestException("Cannot transfer money from to the same account.")
        }

        val fromAccount = repository.findById(transfer.fromAccount!!)
        val toAccount = repository.findById(transfer.toAccount!!)
        val fromAccountCurrency = fromAccount.currency.toString()
        val toAccountCurrency = toAccount.currency.toString()
        val transferCurrency = transfer.currency.toString()
        if (fromAccountCurrency != toAccountCurrency) {
            throw BadRequestException("Accounts should have the same currency.")
        }
        if (fromAccountCurrency != transferCurrency) {
            throw BadRequestException("Currency of transfer should match with account's currency.")
        }
    }
}
