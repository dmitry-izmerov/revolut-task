package revolut.task.service

import revolut.task.model.Transfer

interface TransferService {
    fun getTransfers(fromAccount: Long?, toAccount: Long?): List<Transfer>
    fun getTransfer(id: Long): Transfer
    fun makeTransfer(transfer: Transfer)
}