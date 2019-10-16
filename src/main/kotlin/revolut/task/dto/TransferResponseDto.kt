package revolut.task.dto

data class TransferResponseDto(
    var id: Long? = null,
    var fromAccount: Long? = null,
    var toAccount: Long? = null,
    var amount: String? = null,
    var currency: String? = null,
    var date: String? = null
)
