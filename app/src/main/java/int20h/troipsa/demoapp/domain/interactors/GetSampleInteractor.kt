package int20h.troipsa.demoapp.domain.interactors

import int20h.troipsa.demoapp.data.local.mapper.SampleMapper
import int20h.troipsa.demoapp.data.local.mapper.UserEntityMapper
import int20h.troipsa.demoapp.data.repository.DataRepository
import int20h.troipsa.demoapp.data.repository.sample.getAllSamples
import int20h.troipsa.demoapp.data.repository.sample.getSample
import int20h.troipsa.demoapp.data.repository.user.getCurrentUserFlow
import int20h.troipsa.demoapp.domain.models.Sample
import int20h.troipsa.demoapp.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSampleInteractor@Inject constructor(
    private val dataRepository: DataRepository,
) {
    operator fun invoke(id: Int): Flow<Sample> {
        return dataRepository.getSample(id = id).map { SampleMapper.mapToDomain(it) }
    }

    fun getAllSamples(): Flow<List<Sample>> {
        return dataRepository.getAllSamples()
            .map { samples ->
                    samples.map { SampleMapper.mapToDomain(it)
                }
            }
    }
}
