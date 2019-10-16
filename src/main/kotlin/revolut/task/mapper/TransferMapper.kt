package revolut.task.mapper

import org.mapstruct.*
import org.mapstruct.factory.Mappers
import revolut.task.db.tables.pojos.Transfers
import revolut.task.dto.TransferRequestDto
import revolut.task.dto.TransferResponseDto
import revolut.task.model.Transfer

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface TransferMapper {

    companion object {
        val INSTANCE = Mappers.getMapper(TransferMapper::class.java)
    }

    @Mapping(target = "date", expression = "java( model.getDate() != null ? model.getDate().format(java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME) : null )")
    fun toDto(model: Transfer): TransferResponseDto

    fun toDto(models: Collection<Transfer>): Collection<TransferResponseDto>

    @Mappings(
        Mapping(expression = "java( java.util.Currency.getInstance(dto.getCurrency()) )", target = "currency"),
        Mapping(expression = "java( java.time.OffsetDateTime.now() )", target = "date")
    )
    fun toModel(dto: TransferRequestDto): Transfer

    @Mapping(expression = "java( java.util.Currency.getInstance(entity.getCurrency()) )", target = "currency")
    fun toModel(entity: Transfers): Transfer
}