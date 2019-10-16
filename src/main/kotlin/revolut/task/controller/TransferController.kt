package revolut.task.controller

import io.micronaut.http.annotation.*
import revolut.task.dto.TransferRequestDto
import revolut.task.dto.TransferResponseDto
import revolut.task.mapper.TransferMapper
import revolut.task.service.TransferService
import javax.inject.Inject

@Controller("/transfers")
class TransferController(@Inject private val service: TransferService) {

    @Get
    fun getTransfers(@QueryValue fromAccount: Long?, @QueryValue toAccount: Long?): Collection<TransferResponseDto> {
        val transfers = service.getTransfers(fromAccount, toAccount)
        return TransferMapper.INSTANCE.toDto(transfers)
    }

    @Get("/{id}")
    fun getTransfer(@PathVariable id: Long): TransferResponseDto {
        val transfer = service.getTransfer(id)
        return TransferMapper.INSTANCE.toDto(transfer)
    }

    @Post
    fun makeTransfer(@Body dto: TransferRequestDto) {
        val model = TransferMapper.INSTANCE.toModel(dto)
        service.makeTransfer(model)
    }
}