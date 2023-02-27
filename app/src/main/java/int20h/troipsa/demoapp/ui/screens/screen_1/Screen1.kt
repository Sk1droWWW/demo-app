package int20h.troipsa.demoapp.ui.screens.screen_1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import int20h.troipsa.demoapp.ui.base.ui.PseudoScaffold
import int20h.troipsa.demoapp.ui.theme.*
import int20h.troipsa.demoapp.utils.extension.clickable

@Composable
fun Screen1(
    navController: NavHostController,
    navigateToScreen2: (Long) -> Unit,
) {
    PseudoScaffold(
        modifier = Modifier.systemBarsPadding(),
    ) {
        val viewModel = hiltViewModel<Screen1ViewModel>()

        Screen1(
            navigateToScreen2 = navigateToScreen2,
        )
    }
}

@Composable
private fun Screen1(
    navigateToScreen2: (Long) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(pageBackgroundColor),
    ) {
        Text(
            text = "Another screen",
            fontSize = 12.sp,
            modifier = Modifier.clickable { navigateToScreen2(0) }
        )
    }
}

@Preview
@Composable
private fun Preview() {
    Screen1(
        navigateToScreen2 = {},
    )
}

