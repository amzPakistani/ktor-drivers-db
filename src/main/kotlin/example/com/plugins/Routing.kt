package example.com.plugins

import example.com.data.DriverDataSource
import example.com.routes.createDriver
import example.com.routes.deleteDriver
import example.com.routes.getDriver
import example.com.routes.updateDriver
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(dataSource: DriverDataSource) {
    routing {
        getDriver(dataSource)
        createDriver(dataSource)
        deleteDriver(dataSource)
        updateDriver(dataSource)
    }
}
