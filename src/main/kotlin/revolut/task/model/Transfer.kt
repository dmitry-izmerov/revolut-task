package revolut.task.model

import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.*

data class Transfer(
    var id: Long? = null,
    var fromAccount: Long? = null,
    var toAccount: Long? = null,
    var currency: Currency? = null,
    var amount: BigDecimal? = null,
    var date: OffsetDateTime? = null
)