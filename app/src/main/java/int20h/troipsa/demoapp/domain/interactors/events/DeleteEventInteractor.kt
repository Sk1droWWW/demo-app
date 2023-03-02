package int20h.troipsa.demoapp.domain.interactors.events

import int20h.troipsa.demoapp.data.repository.DataRepository
import int20h.troipsa.demoapp.data.repository.events.deleteEvent
import javax.inject.Inject


class DeleteEventInteractor @Inject constructor(
    private val dataRepository: DataRepository
) {
    suspend operator fun invoke(eventId: Int) {
        dataRepository.deleteEvent(eventId)
    }
}