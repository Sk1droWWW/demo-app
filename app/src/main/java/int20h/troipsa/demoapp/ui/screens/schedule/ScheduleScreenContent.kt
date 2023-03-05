package int20h.troipsa.demoapp.ui.screens.schedule

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import int20h.troipsa.demoapp.R
import int20h.troipsa.demoapp.ui.base.ui.PseudoScaffold
import int20h.troipsa.demoapp.ui.base.ui.defaultTopBarProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleScreenContent(
    popBackStack: () -> Unit,
) {
    val viewModel = hiltViewModel<ScheduleScreenViewModel>()
    val groups by viewModel.groups.collectAsState()

    BackHandler(
        enabled = true,
        onBack = { popBackStack() }
    )

    PseudoScaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = defaultTopBarProvider(
            closeIcon = painterResource(id = R.drawable.ic_back_arrow),
            closeAction = { popBackStack() },
        )
    ) {
        LazyColumn {
            items(items = groups) {
                Text(text = "Item ${it.name}")
            }
        }
    }
}
