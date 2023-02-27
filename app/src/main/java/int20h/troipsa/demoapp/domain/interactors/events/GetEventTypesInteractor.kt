package int20h.troipsa.demoapp.domain.interactors.events

import int20h.troipsa.demoapp.data.local.mapper.EventMapper
import int20h.troipsa.demoapp.data.repository.DataRepository
import int20h.troipsa.demoapp.data.repository.getEventTypes
import int20h.troipsa.demoapp.domain.models.EventType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetEventTypesInteractor @Inject constructor(
    val repository: DataRepository
) {
    operator fun invoke() : Flow<List<EventType>> {
        return repository.getEventTypes().map {
            it.map { eventTypeEntity ->
                EventMapper.mapToDomain(eventTypeEntity)
            }
        }
    }
}
