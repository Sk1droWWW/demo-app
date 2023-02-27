package int20h.troipsa.demoapp.ui.main

import dagger.hilt.android.lifecycle.HiltViewModel
import int20h.troipsa.demoapp.data.prefs.DemoPrefManager
import int20h.troipsa.demoapp.data.prefs.base.get
import int20h.troipsa.demoapp.data.prefs.base.set
import int20h.troipsa.demoapp.domain.interactors.*
import int20h.troipsa.demoapp.domain.interactors.events.AddEventInteractor
import int20h.troipsa.demoapp.domain.interactors.user.GetUserFlowInteractor
import int20h.troipsa.demoapp.domain.models.Event
import int20h.troipsa.demoapp.ui.base.view_model.BaseViewModel
import int20h.troipsa.demoapp.utils.coroutines.launchAndCollect
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getUserFlowInteractor: GetUserFlowInteractor,
    private val prefsManager: DemoPrefManager,
) : BaseViewModel() {

    val user = getUserFlowInteractor()
        .stateIn(scope = scope, SharingStarted.Eagerly, null)


}