package int20h.troipsa.demoapp.domain.interactors.events

import int20h.troipsa.demoapp.data.local.mapper.EventMapper
import int20h.troipsa.demoapp.data.repository.DataRepository
import int20h.troipsa.demoapp.data.repository.getEvent
import int20h.troipsa.demoapp.domain.models.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetEventInteractor @Inject constructor(
    private val dataRepository: DataRepository
) {
    operator fun invoke(eventId: Int): Flow<Event> {
        return dataRepository.getEvent(eventId).map { projection ->
            EventMapper.mapToDomain(
                eventEntity = projection.event,
                eventType = projection.eventType
            )
        }
    }
}