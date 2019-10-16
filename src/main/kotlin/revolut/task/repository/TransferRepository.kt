package revolut.task.repository

import revolut.task.model.Transfer

interface TransferRepository {
    fun findByOrAll(fromAccount: Long?, toAccount: Long?): List<Transfer>
    fun findById(id: Long): Transfer
    fun create(transfer: Transfer)
}
