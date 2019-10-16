package revolut.task.model

import java.math.BigDecimal
import java.util.*

data class Account(
    var id: Long? = null,
    var name: String? = null,
    var currency: Currency? = null,
    var balance: BigDecimal? = null
)