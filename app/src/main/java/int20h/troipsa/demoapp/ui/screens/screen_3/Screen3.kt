package int20h.troipsa.demoapp.ui.screens.screen_3

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
import androidx.navigation.NavHostController
import int20h.troipsa.demoapp.ui.base.ui.PseudoScaffold
import int20h.troipsa.demoapp.ui.components.PrimaryButton
import int20h.troipsa.demoapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen3(
    popBackStack: () -> Unit,
    argumentFromScreen1: Int?,
) {
    PseudoScaffold(
        modifier = Modifier.systemBarsPadding(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "Screen 3, argument: $argumentFromScreen1",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.primaryContainer)
                    .padding(16.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
private fun Screen1(
    navigateToScreen2: (Long) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    LazyColumn(
        modifier = Modifier
            .background(color = Color.Cyan.copy(alpha = 0.2f))
            .fillMaxSize()
//            .background(pageBackgroundColor),
    ) {
        item {
            PrimaryButton(
                text = "Sample Button",
                onClick = { /*TODO*/ },
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
    Screen1(
        navigateToScreen2 = {},
    )
}

