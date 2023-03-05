package int20h.troipsa.demoapp.domain.interactors.groups

import int20h.troipsa.demoapp.data.local.mapper.GroupMapper
import int20h.troipsa.demoapp.data.repository.DataRepository
import int20h.troipsa.demoapp.data.repository.group.fetchGroupsFromApi
import int20h.troipsa.demoapp.domain.models.Group
import javax.inject.Inject


class GetGroupsInteractor @Inject constructor(
    private val dataRepository: DataRepository,
) {
    suspend operator fun invoke(): List<Group> {
        val k = dataRepository.fetchGroupsFromApi().data
            .map { group -> GroupMapper.mapToDomain(group) }
        return k
    }
}