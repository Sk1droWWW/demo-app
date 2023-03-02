package int20h.troipsa.demoapp.domain.interactors.events

import int20h.troipsa.demoapp.data.local.mapper.EventMapper
import int20h.troipsa.demoapp.data.repository.DataRepository
import int20h.troipsa.demoapp.data.repository.events.addEventType
import int20h.troipsa.demoapp.domain.models.EventType
import javax.inject.Inject

class AddEventTypeInteractor @Inject constructor(
    val dataRepository: DataRepository
){
    suspend operator fun invoke(eventType: EventType) {
        dataRepository.addEventType(EventMapper.mapToEntity(eventType))
    }

    suspend operator fun invoke(eventTypeList: List<EventType>) {
        eventTypeList.forEach { eventType ->
            dataRepository.addEventType(
                EventMapper.mapToEntity(eventType)
            )
        }
    }
}