package int20h.troipsa.demoapp.data.network.models

import com.google.gson.annotations.SerializedName

data class GroupDataApiModel(
    @SerializedName("data")
    val data: List<GroupApiModel>
)

data class GroupApiModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("faculty")
    val faculty: String,
)