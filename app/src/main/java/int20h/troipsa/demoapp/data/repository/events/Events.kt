package int20h.troipsa.demoapp.data.repository.events

import int20h.troipsa.demoapp.data.local.entity.EventEntity
import int20h.troipsa.demoapp.data.local.entity.EventTypeEntity
import int20h.troipsa.demoapp.data.local.entity.EventWithTypeProjection
import int20h.troipsa.demoapp.data.repository.DataRepository
import kotlinx.coroutines.flow.Flow

fun DataRepository.getEvent(eventId: Int): Flow<EventWithTypeProjection> {
    return mainDatabase.eventsDao().getEvent(eventId)
}

fun DataRepository.getEvents(): Flow<List<EventWithTypeProjection>> {
    return mainDatabase.eventsDao().getEvents()
}

suspend fun DataRepository.addEvent(event: EventEntity) {
    mainDatabase.eventsDao().insertOrReplace(event)
}

suspend fun DataRepository.deleteEvent(eventId: Int) {
    mainDatabase.eventsDao().deleteEvent(eventId)
}

suspend fun DataRepository.addEventType(eventType: EventTypeEntity) {
    mainDatabase.eventsTypeDao().insertOrReplace(eventType)
}

fun DataRepository.getEventTypes(): Flow<List<EventTypeEntity>> {
    return mainDatabase.eventsTypeDao().getEventTypes()
}