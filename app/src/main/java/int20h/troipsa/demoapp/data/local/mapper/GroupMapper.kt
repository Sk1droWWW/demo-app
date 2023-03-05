package int20h.troipsa.demoapp.data.local.mapper

import int20h.troipsa.demoapp.data.network.models.GroupApiModel
import int20h.troipsa.demoapp.domain.models.Group

object GroupMapper {
    fun mapToDomain(groupApiModel: GroupApiModel) = Group(
        id = groupApiModel.id,
        name = groupApiModel.name,
        faculty = groupApiModel.faculty,
    )
}