package revolut.task.dto

data class AccountDto(
    var id: Long? = null,
    var name: String? = null,
    var currency: String? = null,
    var balance: String? = null
)
