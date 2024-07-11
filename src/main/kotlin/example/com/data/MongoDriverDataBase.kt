package example.com.data

import com.mongodb.client.model.Filters.eq
import com.mongodb.client.model.Updates.set
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

    override suspend fun updateDriver(driver: Driver): Boolean {
        val updateResult = drivers.updateOne(
            eq("name", driver.name),
            com.mongodb.client.model.Updates.combine(
                set("titles", driver.titles),
                set("wins", driver.wins)
            )
        )
        return updateResult.wasAcknowledged() && updateResult.modifiedCount > 0
    }
}