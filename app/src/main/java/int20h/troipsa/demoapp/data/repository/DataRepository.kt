package int20h.troipsa.demoapp.data.repository

import int20h.troipsa.demoapp.data.local.MainDatabase
import int20h.troipsa.demoapp.data.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(
    val apiService: ApiService,
    val mainDatabase: MainDatabase,
)




