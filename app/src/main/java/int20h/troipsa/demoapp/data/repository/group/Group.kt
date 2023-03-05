package int20h.troipsa.demoapp.data.repository.group

import int20h.troipsa.demoapp.data.network.models.GroupDataApiModel
import int20h.troipsa.demoapp.data.repository.DataRepository

suspend fun DataRepository.fetchGroupsFromApi(): GroupDataApiModel {
    return apiService.getGroups()
}

