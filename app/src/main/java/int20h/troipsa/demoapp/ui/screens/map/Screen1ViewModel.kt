package int20h.troipsa.demoapp.ui.screens.map

import dagger.hilt.android.lifecycle.HiltViewModel
import int20h.troipsa.demoapp.domain.interactors.GetSampleInteractor
import int20h.troipsa.demoapp.ui.base.view_model.BaseViewModel
import kotlinx.coroutines.flow.SharingStarted
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