package int20h.troipsa.demoapp.ui.screens.map_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import int20h.troipsa.demoapp.R
import int20h.troipsa.demoapp.ui.base.ui.PseudoScaffold
import int20h.troipsa.demoapp.ui.screens.suggest_screen.SuggestLocationDialog
import int20h.troipsa.demoapp.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreenContent(
    showBottomSheet: (Boolean) -> Unit,
//    navigateToScreen2: (Long) -> Unit,
//    navigateToScreen3: (Int) -> Unit,
) {
    PseudoScaffold(
//        modifier = Modifier.navigationBarsPadding(),
    ) {
        val viewModel = hiltViewModel<Screen1ViewModel>()

        val allSamples = viewModel.allSample.collectAsState()
        var sheetVisible by remember { mutableStateOf(false) }
        val scope = rememberCoroutineScope()


        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Red.copy(alpha = 0.2f))
            )

            AnimatedVisibility(
                visible = sheetVisible,
                enter = slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(durationMillis = 400)
                ),
                exit = slideOutVertically(
                    targetOffsetY = { it },
                    animationSpec = tween(durationMillis = 400)
                )
            ) {
                SuggestLocationDialog(
                    onDismiss = {
                        showBottomSheet(true)
                        sheetVisible = false
                    },
                )
            }

            SuggestLocationFab(
                visible = !sheetVisible,
                onClick = {
                    scope.launch {
                        sheetVisible = true
                        showBottomSheet(false)
                    }
                },
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomCenter)
            )
        }

    }

}

@Composable
private fun SuggestLocationFab(
    onClick: () -> Unit,
    visible: Boolean,
    modifier: Modifier = Modifier,
) {
    if (visible) {
        ExtendedFloatingActionButton(
            text = {
                Text(
                    text = "Suggest Location",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = MaterialTheme.typography.labelLarge,
                )
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_suggest_location),
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    contentDescription = null,
                )
            },
            onClick = onClick,
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            shape = RoundedCornerShape(16.dp),
            modifier = modifier
        )
    }
}


@Preview
@Composable
private fun Preview() {
//    Screen1(
//        navigateToScreen2 = {},
//    )
}

