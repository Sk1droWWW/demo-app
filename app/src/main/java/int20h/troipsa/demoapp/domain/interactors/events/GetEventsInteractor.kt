package int20h.troipsa.demoapp.domain.interactors.events

import int20h.troipsa.demoapp.data.local.mapper.EventMapper
import int20h.troipsa.demoapp.data.repository.DataRepository
import int20h.troipsa.demoapp.data.repository.getEvents
import int20h.troipsa.demoapp.domain.models.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class GetEventsInteractor @Inject constructor(
    private val dataRepository: DataRepository
) {

    operator fun invoke(): Flow<Map<LocalDate, List<Event>>> {
        return dataRepository.getEvents().map { list ->
            list.map { projection ->
                EventMapper.mapToDomain(
                    eventEntity = projection.event,
                    eventType = projection.eventType
                )
            }.groupBy { item -> item.startTime.toLocalDate() }
        }
    }

}
