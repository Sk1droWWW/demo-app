package int20h.troipsa.demoapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun DemoAppTheme(
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    DisposableEffect(systemUiController) {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = true
        )

        onDispose { }
    }

    MaterialTheme(
        colorScheme = DemoAppColorScheme,
        typography = DemoTypography,
        shapes = DemoShapes,
        content = content
    )
}
