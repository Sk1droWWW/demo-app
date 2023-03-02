package int20h.troipsa.demoapp.data.local.mapper

import int20h.troipsa.demoapp.data.local.entity.SampleEntity
import int20h.troipsa.demoapp.domain.models.Sample

object SampleMapper {

    fun mapToDomain(sampleEntity: SampleEntity) = Sample(
        id = sampleEntity.id,
        name = sampleEntity.name,
    )

    fun mapToEntity(sample: Sample) = SampleEntity(
        id = sample.id,
        name = sample.name,
    )
}