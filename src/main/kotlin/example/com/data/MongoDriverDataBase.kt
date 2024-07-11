package example.com.data

import com.mongodb.client.model.Filters.eq
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import example.com.data.model.Driver
import kotlinx.coroutines.flow.firstOrNull

class MongoDriverDataBase(db: MongoDatabase) : DriverDataSource {
    private val drivers = db.getCollection("drivers", Driver::class.java)

    override suspend fun getDriver(name: String): Driver? {
        return drivers.find(eq("name", name)).firstOrNull()
    }

    override suspend fun createDriver(driver: Driver): Boolean {
        return drivers.insertOne(driver).wasAcknowledged()

    }

    override suspend fun deleteDriver(name: String): Boolean {
        val deletedResult = drivers.deleteOne(eq("name", name))
        return deletedResult.deletedCount > 0
    }
}