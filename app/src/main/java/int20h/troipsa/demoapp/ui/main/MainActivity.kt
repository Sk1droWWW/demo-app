package int20h.troipsa.demoapp.ui.main


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import int20h.troipsa.demoapp.ui.navigation.DemoAppNavHost
import int20h.troipsa.demoapp.ui.theme.DemoAppTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WindowCompat.setDecorFitsSystemWindows(window, false)
            DemoAppTheme {
                DemoAppNavHost()
            }
        }
    }

}


