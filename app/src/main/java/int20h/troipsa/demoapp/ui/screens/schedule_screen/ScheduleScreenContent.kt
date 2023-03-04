package int20h.troipsa.demoapp.ui.screens.schedule_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import int20h.troipsa.demoapp.R
import int20h.troipsa.demoapp.ui.base.ui.PseudoScaffold
import int20h.troipsa.demoapp.ui.base.ui.defaultTopBarProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleScreenContent(
    popBackStack: () -> Unit,
) {

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
        Box(
            modifier = Modifier
                .background(color = Color.Red.copy(alpha = 0.2f))
                .fillMaxSize())
//        Text(
//            text = "arg from screen 1 = $argFromScreen1",
//            style = MaterialTheme.typography.bodySmall.medium(),
//            color = Color.White,
//            textAlign = TextAlign.Start,
//            overflow = TextOverflow.Ellipsis,
//            maxLines = 1,
//            modifier = Modifier
//                .padding(horizontal = 16.dp, vertical = 4.dp)
////                .background(
////                    shape = RoundedCornerShape(8.dp)
////                )
//                .clip(RoundedCornerShape(8.dp))
//                .fillMaxWidth()
//        )
    }
}
