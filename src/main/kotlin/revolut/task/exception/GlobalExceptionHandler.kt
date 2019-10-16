package revolut.task.exception

import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Produces
import io.micronaut.http.server.exceptions.ExceptionHandler
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Produces
@Singleton
@Requires(classes = [ExceptionHandler::class])
class GlobalExceptionHandler : ExceptionHandler<Exception, HttpResponse<*>> {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun handle(request: HttpRequest<*>?, exception: Exception?): HttpResponse<*> {
        logger.error("Error happened", exception)
        return HttpResponse.serverError<Any>()
    }
}