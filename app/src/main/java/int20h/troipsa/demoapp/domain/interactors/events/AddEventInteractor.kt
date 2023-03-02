package int20h.troipsa.demoapp.domain.interactors.events

import int20h.troipsa.demoapp.data.repository.DataRepository
import int20h.troipsa.demoapp.domain.models.Event
import javax.inject.Inject

class AddEventInteractor @Inject constructor(
    private val repository: DataRepository,
) {
    suspend operator fun invoke(event: Event) {

    }

    suspend operator fun invoke(events: List<Event>) {
        events.forEach { event ->
            invoke(event)
        }
    }

}