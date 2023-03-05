package int20h.troipsa.demoapp.ui.screens.schedule_screen

import dagger.hilt.android.lifecycle.HiltViewModel
import int20h.troipsa.demoapp.domain.interactors.groups.GetGroupsInteractor
import int20h.troipsa.demoapp.domain.models.Group
import int20h.troipsa.demoapp.ui.base.view_model.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ScheduleScreenViewModel @Inject constructor(
    private val getGroupsInteractor: GetGroupsInteractor,
//    private val someInteractor: SomeInteractor,
) : BaseViewModel() {
    private val _groups = MutableStateFlow<List<Group>>(emptyList())
    val groups = _groups.asStateFlow()
    init {
        runCoroutine { _groups.value = getGroupsInteractor() }
    }

    companion object {

    }
}