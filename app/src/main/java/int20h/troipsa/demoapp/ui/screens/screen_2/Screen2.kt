package int20h.troipsa.demoapp.ui.screens.screen_2

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import int20h.troipsa.demoapp.R
import int20h.troipsa.demoapp.ui.base.ui.PseudoScaffold
import int20h.troipsa.demoapp.ui.base.ui.defaultTopBarProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen2(
    argFromScreen1: Int?,
    popBackStack: () -> Unit,
) {

    BackHandler(
        enabled = true,
        onBack = { popBackStack() }
    )

    PseudoScaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = defaultTopBarProvider(
            closeIcon = painterResource(id = R.drawable.ic_back_arrow),
            closeAction = { popBackStack() },
        )
    ) {
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
