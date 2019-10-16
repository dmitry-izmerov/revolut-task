package revolut.task.mapper

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.factory.Mappers
import revolut.task.db.tables.pojos.Accounts
import revolut.task.dto.AccountDto
import revolut.task.model.Account

@Mapper
interface AccountMapper {

    companion object {
        val INSTANCE = Mappers.getMapper(AccountMapper::class.java)
    }

    @Mappings(
        Mapping(expression = "java( model.getCurrency().toString() )", target = "currency"),
        Mapping(expression = "java( model.getBalance().toString() )", target = "balance")
    )
    fun toDto(model: Account): AccountDto

    fun toDto(models: List<Account>): List<AccountDto>

    @Mappings(
        Mapping(expression = "java( java.util.Currency.getInstance(dto.getCurrency()) )", target = "currency"),
        Mapping(expression = "java( new java.math.BigDecimal(dto.getBalance()) )", target = "balance")
    )
    fun toModel(dto: AccountDto): Account

    @Mapping(expression = "java( java.util.Currency.getInstance(entity.getCurrency()) )", target = "currency")
    fun toModel(entity: Accounts): Account

    fun toModel(entities: List<Accounts>): List<Account>
}