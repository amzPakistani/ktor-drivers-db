package example.com.data.model

import org.bson.types.ObjectId
import org.bson.codecs.pojo.annotations.BsonId


data class Driver(
    val name:String,
    val titles:String,
    val wins:String,
    @BsonId val id: ObjectId = ObjectId()
)
