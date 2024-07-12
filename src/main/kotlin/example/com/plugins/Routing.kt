package example.com.plugins

import example.com.data.DriverDataSource
import example.com.routes.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(dataSource: DriverDataSource) {
    routing {
        getDriver(dataSource)
        createDriver(dataSource)
        deleteDriver(dataSource)
        updateDriver(dataSource)
        getDrivers(dataSource)
    }
}
