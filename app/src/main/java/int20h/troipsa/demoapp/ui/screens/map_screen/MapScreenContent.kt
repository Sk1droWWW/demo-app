package int20h.troipsa.demoapp.ui.screens.map_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import int20h.troipsa.demoapp.ui.base.ui.PseudoScaffold
import int20h.troipsa.demoapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreenContent(
    navController: NavHostController,
//    navigateToScreen2: (Long) -> Unit,
//    navigateToScreen3: (Int) -> Unit,
) {
    PseudoScaffold(
        modifier = Modifier.systemBarsPadding(),
    ) {
        val viewModel = hiltViewModel<Screen1ViewModel>()

        val allSamples = viewModel.allSample.collectAsState()

        MapScreenContent(
//            navigateToScreen2 = navigateToScreen2,
//            navigateToScreen3 = navigateToScreen3,
        )
    }
}

@Composable
private fun MapScreenContent(
//    navigateToScreen2: (Long) -> Unit,
//    navigateToScreen3: (Int) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    LazyColumn(
        modifier = Modifier
            .background(color = Color.Cyan.copy(alpha = 0.2f))
            .fillMaxSize()
//            .background(pageBackgroundColor),
    ) {
//        item {
//            SecondaryTextButton(
//                text = "Sample Button",
////                onClick = { navigateToScreen3(3) },
//                modifier = Modifier
//                    .padding(16.dp)
//                    .fillMaxWidth()
//            )
//        }
//        repeat(20) {
//            item {
//                Text(
//                    text = "Another screen",
//                    fontSize = 12.sp,
//                    color = MaterialTheme.colorScheme.onPrimaryContainer,
//                    modifier = Modifier
//                        .background(MaterialTheme.colorScheme.primaryContainer)
//                        .padding(16.dp)
////                        .clickable { navigateToScreen2(0) }
//                )
//            }
//        }
    }
}

@Preview
@Composable
private fun Preview() {
//    Screen1(
//        navigateToScreen2 = {},
//    )
}

