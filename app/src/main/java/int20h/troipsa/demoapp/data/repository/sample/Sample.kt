package int20h.troipsa.demoapp.data.repository.sample

import int20h.troipsa.demoapp.data.local.entity.EventWithTypeProjection
import int20h.troipsa.demoapp.data.local.entity.SampleEntity
import int20h.troipsa.demoapp.data.repository.DataRepository
import kotlinx.coroutines.flow.Flow


fun DataRepository.getSample(id: Int): Flow<SampleEntity> {
    return mainDatabase.sampleDao().getSample(id)
}
fun DataRepository.getAllSamples(): Flow<List<SampleEntity>> {
    return mainDatabase.sampleDao().getAllSamples()
}