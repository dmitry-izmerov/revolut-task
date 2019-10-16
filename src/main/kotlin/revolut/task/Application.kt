package revolut.task

import io.micronaut.context.env.Environment
import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
            .environments(Environment.DEVELOPMENT)
            .packages("revolut.task")
            .mainClass(Application.javaClass)
            .start()
    }
}