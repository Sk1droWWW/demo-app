package int20h.troipsa.demoapp.ui.screens.screen_1

import dagger.hilt.android.lifecycle.HiltViewModel
import int20h.troipsa.demoapp.domain.interactors.GetSampleInteractor
import int20h.troipsa.demoapp.domain.interactors.events.AddEventTypeInteractor
import int20h.troipsa.demoapp.domain.interactors.events.GetEventTypesInteractor
import int20h.troipsa.demoapp.domain.interactors.events.GetEventsInteractor
import int20h.troipsa.demoapp.domain.models.EventType
import int20h.troipsa.demoapp.ui.base.view_model.BaseViewModel
import int20h.troipsa.demoapp.utils.coroutines.launchAndCollect
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class Screen1ViewModel @Inject constructor(
//    private val someInteractor: SomeInteractor,
//    private val anotherInteractor: AnotherInteractor
    private val sampleGetInteractor: GetSampleInteractor,
) : BaseViewModel() {

//    val sampleObject = MutableStateFlow<SampleObject?>(null)
//
    val allSample = sampleGetInteractor
        .getAllSamples()
        .stateIn(scope, SharingStarted.Eagerly, emptyList())

    init {
        val sample = sampleGetInteractor(id = 0)
    }

}