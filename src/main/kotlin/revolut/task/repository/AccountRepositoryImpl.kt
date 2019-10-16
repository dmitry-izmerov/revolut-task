package revolut.task.repository

import org.jooq.impl.DSL.using
import revolut.task.db.Public.PUBLIC
import revolut.task.db.Tables.ACCOUNTS
import revolut.task.db.tables.daos.AccountsDao
import revolut.task.exception.NotFoundException
import revolut.task.mapper.AccountMapper
import revolut.task.model.Account
import java.math.BigDecimal
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountRepositoryImpl(@Inject private val accountsDao: AccountsDao) : AccountRepository {

    override fun findAll(): List<Account> {
        val entities = accountsDao.findAll()
        return AccountMapper.INSTANCE.toModel(entities)
    }

    override fun findById(id: Long): Account {
        val entity = accountsDao.fetchOneById(id) ?: throw NotFoundException("Account with id: $id was not found.")
        return AccountMapper.INSTANCE.toModel(entity)
    }

    override fun create(account: Account): Long {
        val (id, _, _, _) = using(accountsDao.configuration())
            .insertInto(accountsDao.table, ACCOUNTS.NAME, ACCOUNTS.CURRENCY, ACCOUNTS.BALANCE)
            .values(account.name, account.currency.toString(), account.balance)
            .returning(ACCOUNTS.ID)
            .fetchOne()
        return id
    }

    override fun deleteById(id: Long) {
        accountsDao.deleteById(id)
    }

    override fun transfer(fromAccount: Long, toAccount: Long, currency: String, amount: BigDecimal) {
        using(accountsDao.configuration())
            .transaction { configuration ->
                val firstAccountId = if (fromAccount < toAccount) fromAccount else toAccount
                val secondAccountId = if (fromAccount > toAccount) fromAccount else toAccount
                val firstOperation = if (firstAccountId == fromAccount) ACCOUNTS.BALANCE.minus(amount) else ACCOUNTS.BALANCE.plus(amount)
                val secondOperation = if (secondAccountId == fromAccount) ACCOUNTS.BALANCE.minus(amount) else ACCOUNTS.BALANCE.plus(amount)

                using(configuration)
                    .update(ACCOUNTS)
                    .set(ACCOUNTS.BALANCE, firstOperation)
                    .where(ACCOUNTS.ID.eq(firstAccountId))
                    .execute()

                using(configuration)
                    .update(ACCOUNTS)
                    .set(ACCOUNTS.BALANCE, secondOperation)
                    .where(ACCOUNTS.ID.eq(secondAccountId))
                    .execute()
            }
    }
}