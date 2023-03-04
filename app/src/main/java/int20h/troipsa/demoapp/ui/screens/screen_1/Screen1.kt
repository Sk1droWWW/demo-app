package int20h.troipsa.demoapp.ui.screens.screen_1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import int20h.troipsa.demoapp.ui.base.ui.PseudoScaffold
import int20h.troipsa.demoapp.ui.components.PrimaryButton
import int20h.troipsa.demoapp.ui.components.SecondaryButton
import int20h.troipsa.demoapp.ui.components.SecondaryTextButton
import int20h.troipsa.demoapp.ui.theme.*
import int20h.troipsa.demoapp.utils.extension.clickable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen1(
    navController: NavHostController,
    navigateToScreen2: (Long) -> Unit,
    navigateToScreen3: (Int) -> Unit,
) {
    PseudoScaffold(
        modifier = Modifier.systemBarsPadding(),
    ) {
        val viewModel = hiltViewModel<Screen1ViewModel>()

        val allSamples = viewModel.allSample.collectAsState()

        Screen1(
            navigateToScreen2 = navigateToScreen2,
            navigateToScreen3 = navigateToScreen3,
        )
    }
}

@Composable
private fun Screen1(
    navigateToScreen2: (Long) -> Unit,
    navigateToScreen3: (Int) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    LazyColumn(
        modifier = Modifier
            .background(color = Color.Cyan.copy(alpha = 0.2f))
            .fillMaxSize()
//            .background(pageBackgroundColor),
    ) {
        item {
            SecondaryTextButton(
                text = "Sample Button",
                onClick = { navigateToScreen3(3) },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
        }
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

