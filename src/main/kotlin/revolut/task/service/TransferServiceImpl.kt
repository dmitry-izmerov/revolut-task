package revolut.task.service

import revolut.task.model.Transfer
import revolut.task.repository.TransferRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransferServiceImpl(
        @Inject private val accountService: AccountService,
        @Inject private val repository: TransferRepository
    ) : TransferService {

    override fun getTransfers(fromAccount: Long?, toAccount: Long?): List<Transfer> {
        return repository.findByOrAll(fromAccount, toAccount)
    }

    override fun getTransfer(id: Long): Transfer {
        return repository.findById(id)
    }

    override fun makeTransfer(transfer: Transfer) {
        accountService.transfer(transfer)
        repository.create(transfer)
    }
}