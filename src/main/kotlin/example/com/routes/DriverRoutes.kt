package example.com.routes

import example.com.data.DriverDataSource
import example.com.data.model.Driver
import example.com.data.requests.DriverRequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getDriver(dataSource: DriverDataSource){
    get("get-driver"){
        val driverRequest = call.receive<DriverRequest>()
        val driver = dataSource.getDriver(driverRequest.name)
        if(driver==null){
            call.respond(HttpStatusCode.NotFound, "Driver not found")
        }else{
            call.respond(HttpStatusCode.OK, driver)
        }
    }
}

fun Route.getDrivers(dataSource: DriverDataSource){
    get("get-drivers"){
        val drivers = dataSource.getDrivers()
        if(drivers==null){
            call.respond(HttpStatusCode.NotFound, "Drivers are empty")
        }else{
            call.respond(HttpStatusCode.OK, drivers)
        }
    }
}

fun Route.createDriver(dataSource: DriverDataSource){
    post("create-driver"){
        val driverRequest = call.receive<Driver>()
        val driver = Driver(
            name = driverRequest.name,
            titles = driverRequest.titles,
            wins = driverRequest.wins
        )
        val result = dataSource.createDriver(driver)
        if(result){
            call.respond(HttpStatusCode.OK, driver)
        }else{
            call.respond(HttpStatusCode.InternalServerError, "Couldn't create driver")
        }
    }
}

fun Route.deleteDriver(dataSource: DriverDataSource){
    delete("delete-driver") {
        val name = call.parameters["name"]
        if (name == null) {
            call.respond(HttpStatusCode.BadRequest, "Missing driver name")
            return@delete
        }
        val deletedDriver = dataSource.deleteDriver(name)
        if (deletedDriver) {
            call.respond(HttpStatusCode.OK, "Driver '$name' deleted successfully")
        } else {
            call.respond(HttpStatusCode.InternalServerError, "Couldn't delete driver")
        }
    }
}

fun Route.updateDriver(dataSource: DriverDataSource){
    post("update-driver"){
        val driverRequest = call.receive<Driver>()
        val newDriver = Driver(
            driverRequest.name,driverRequest.titles,driverRequest.wins
        )
        val result = dataSource.updateDriver(newDriver)
        if(result){
            call.respond(HttpStatusCode.OK, newDriver)
        }else{
            call.respond(HttpStatusCode.InternalServerError, "Couldn't update driver")
        }
    }
}