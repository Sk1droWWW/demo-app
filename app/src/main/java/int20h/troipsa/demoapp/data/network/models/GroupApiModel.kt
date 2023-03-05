package int20h.troipsa.demoapp.data.network.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class GroupDataApiModel(
    @SerialName("data")
    val data: List<GroupApiModel>
)

@Serializable
data class GroupApiModel(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("faculty")
    val faculty: String
)
