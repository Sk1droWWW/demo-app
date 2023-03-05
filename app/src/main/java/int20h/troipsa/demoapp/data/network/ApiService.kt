package int20h.troipsa.demoapp.data.network

import int20h.troipsa.demoapp.data.network.models.GroupApiModel
import int20h.troipsa.demoapp.data.network.models.UserApiModel
import retrofit2.http.GET

interface ApiService {

    @GET("user")
    suspend fun getUser(): UserApiModel

    @GET("schedule/groups")
    suspend fun getGroups(): List<GroupApiModel>

}