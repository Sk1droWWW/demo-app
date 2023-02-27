package int20h.troipsa.demoapp.data.repository.user

import int20h.troipsa.demoapp.data.local.entity.UserEntity
import int20h.troipsa.demoapp.data.local.mapper.UserEntityMapper
import int20h.troipsa.demoapp.data.network.models.UserApiModel
import int20h.troipsa.demoapp.data.repository.DataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


fun DataRepository.getCurrentUserFlow(): Flow<UserEntity?> {
    // TODO: remove stub data
//        return mainDatabase.userDao().getCurrentUser()
    return flow {
        emit(
            UserEntity(
                id = 0,
                name = "Stub User",
                phoneNumber = "32423 32423 234"
            )
        )
    }
}

suspend fun DataRepository.syncUser() {
    val userApiModel = fetchUserFromApi()
    val userEntity = UserEntityMapper.mapToEntity(userApiModel)
    saveUserToDb(userEntity)
}

private suspend fun DataRepository.fetchUserFromApi(): UserApiModel {
    return apiService.getUser()
}

private suspend fun DataRepository.saveUserToDb(user: UserEntity) {
    mainDatabase.userDao().insertOrReplace(user)
}