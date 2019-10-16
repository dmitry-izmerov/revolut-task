package revolut.task.dto

data class TransferRequestDto(
    val fromAccount: Long,
    val toAccount: Long,
    val currency: String,
    val amount: String
)
