package example.com.data

import example.com.data.model.Driver

interface DriverDataSource {
    suspend fun getDriver(name:String):Driver?
    suspend fun createDriver(driver: Driver):Boolean
    suspend fun deleteDriver(name: String):Boolean
    suspend fun updateDriver(driver: Driver):Boolean
    suspend fun getDrivers():List<Driver>?

}