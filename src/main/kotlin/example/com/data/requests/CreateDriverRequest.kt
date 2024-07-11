package example.com.data.requests

import kotlinx.serialization.Serializable


data class CreateDriverRequest(
    val name:String,
    val titles:String,
    val wins:String,
)
