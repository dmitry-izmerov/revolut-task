package revolut.task.service

import revolut.task.model.Account
import revolut.task.model.Transfer

interface AccountService {
    fun getAccounts(): List<Account>
    fun getAccount(id: Long): Account
    fun createAccount(account: Account): Long
    fun deleteAccount(id: Long)
    fun transfer(transfer: Transfer)
}