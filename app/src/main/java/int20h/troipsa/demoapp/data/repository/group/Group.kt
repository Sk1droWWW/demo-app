package int20h.troipsa.demoapp.data.repository.group

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import int20h.troipsa.demoapp.data.network.ApiService
import int20h.troipsa.demoapp.data.network.models.GroupApiModel
import int20h.troipsa.demoapp.data.repository.DataRepository
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

suspend fun DataRepository.fetchGroupsFromApi(): List<GroupApiModel> {
    return apiService.getGroups()
}

