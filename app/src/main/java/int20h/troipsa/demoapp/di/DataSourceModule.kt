package int20h.troipsa.demoapp.di

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import int20h.troipsa.demoapp.BuildConfig
import int20h.troipsa.demoapp.data.local.MainDatabase
import int20h.troipsa.demoapp.data.network.ApiService
import int20h.troipsa.demoapp.data.prefs.DemoPrefManager
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    @Singleton
    fun provideMainDatabase(@ApplicationContext context: Context): MainDatabase {
        return MainDatabase.createInstance(context)
    }

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        val okHttpClient = createOkHttpClient()

        return createRetrofitService(okHttpClient, BuildConfig.BASE_SCHEDULE_URL)
    }

    @Provides
    @Singleton
    fun providePrefsManager(@ApplicationContext context: Context): DemoPrefManager {
        return DemoPrefManager(context)
    }

    @OptIn(ExperimentalSerializationApi::class)
    private inline fun <reified T> createRetrofitService(
        okHttpClient: OkHttpClient,
        baseUrl: String,
    ): T {
        val contentType = "application/json".toMediaType()
        val json = Json { ignoreUnknownKeys = true }

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(okHttpClient)
            .build()

        return retrofit.create(T::class.java)
    }

    private fun createOkHttpClient(action: (OkHttpClient.Builder.() -> Unit)? = null): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .apply {
                action?.invoke(this)
            }
            .build()
    }

}
