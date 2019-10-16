package revolut.task.repository

import revolut.task.model.Account
import java.math.BigDecimal

interface AccountRepository {
    fun findAll(): List<Account>
    fun findById(id: Long): Account
    fun create(account: Account): Long
    fun deleteById(id: Long)
    fun transfer(fromAccount: Long, toAccount: Long, currency: String, amount: BigDecimal)
}