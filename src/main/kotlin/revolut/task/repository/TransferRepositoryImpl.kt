package revolut.task.repository

import org.jooq.Condition
import org.jooq.Record
import org.jooq.impl.DSL.trueCondition
import org.jooq.impl.DSL.using
import revolut.task.db.Tables.TRANSFERS
import revolut.task.db.tables.daos.TransfersDao
import revolut.task.exception.NotFoundException
import revolut.task.mapper.TransferMapper
import revolut.task.model.Transfer
import java.time.OffsetDateTime
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransferRepositoryImpl(@Inject private val dao: TransfersDao) : TransferRepository {

    override fun findByOrAll(fromAccount: Long?, toAccount: Long?): List<Transfer> {
        var condition: Condition = trueCondition()
        if (fromAccount != null) {
            condition = condition.and(TRANSFERS.FROM_ACCOUNT.eq(fromAccount))
        }
        if (toAccount != null) {
            condition = condition.and(TRANSFERS.TO_ACCOUNT.eq(toAccount))
        }
        return using(dao.configuration())
            .select()
            .from(TRANSFERS)
            .where(condition)
            .fetch()
            .map(mapRecord())
    }

    private fun mapRecord(): (Record) -> Transfer {
        return { r ->
            Transfer(r.get(TRANSFERS.ID), r.get(TRANSFERS.FROM_ACCOUNT), r.get(TRANSFERS.TO_ACCOUNT),
                Currency.getInstance(r.get(TRANSFERS.CURRENCY)), r.get(TRANSFERS.AMOUNT), r.get(TRANSFERS.DATE))
        }
    }

    override fun findById(id: Long): Transfer {
        val entity = dao.fetchOneById(id) ?: throw NotFoundException("Transfer with id: $id was not found.")
        return TransferMapper.INSTANCE.toModel(entity)
    }

    override fun create(transfer: Transfer) {
        using(dao.configuration())
            .insertInto(TRANSFERS, TRANSFERS.FROM_ACCOUNT, TRANSFERS.TO_ACCOUNT, TRANSFERS.CURRENCY, TRANSFERS.AMOUNT, TRANSFERS.DATE)
            .values(transfer.fromAccount, transfer.toAccount, transfer.currency.toString(), transfer.amount, OffsetDateTime.now())
            .execute()
    }
}