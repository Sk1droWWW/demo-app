package int20h.troipsa.demoapp.domain.interactors.groups

import android.util.Log
import int20h.troipsa.demoapp.data.local.mapper.GroupMapper
import int20h.troipsa.demoapp.data.repository.DataRepository
import int20h.troipsa.demoapp.data.repository.group.fetchGroupsFromApi
import int20h.troipsa.demoapp.domain.models.Group
import javax.inject.Inject


class GetGroupsInteractor @Inject constructor(
    private val dataRepository: DataRepository,
) {
    suspend operator fun invoke(): List<Group> {
        val k = dataRepository.fetchGroupsFromApi()
            .map { group -> GroupMapper.mapToDomain(group) }
        Log.i("Map", "(${k})")
        return k
    }
}