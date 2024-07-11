package example.com

import com.mongodb.kotlin.client.coroutine.MongoClient
import example.com.data.MongoDriverDataBase
import example.com.plugins.*
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {

    val dbName = "DriverDatabase"
    val db = MongoClient.create(
        connectionString = "mongodb+srv://abdulmajidzeeshan4:tutorial202@cluster0.pxqn3u7.mongodb.net/$dbName?retryWrites=true&w=majority&appName=Cluster0"
    ).getDatabase(dbName)

    val dataSource = MongoDriverDataBase(db)

    configureSerialization()
    configureMonitoring()
    configureRouting(dataSource)
}
