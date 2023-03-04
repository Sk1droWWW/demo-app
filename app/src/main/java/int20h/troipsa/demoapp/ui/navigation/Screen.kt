package int20h.troipsa.demoapp.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import int20h.troipsa.demoapp.R

sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
    @DrawableRes val iconRes: Int? = null,
) {
    object Screen1 : Screen("calendar", R.string.screen_1_string_1, R.drawable.ic_calendar)
    object Screen2 : Screen("screen2", R.string.screen_2_string_1, R.drawable.ic_calendar)

    object Screen3 : Screen("screen3", R.string.screen_1_string_1) {
        val demoArgument = "demoArgument"
    }

    // build navigation path (for screen navigation)
    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

    // build and setup route format (in navigation graph)
    fun withArgsFormat(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/{$arg}")
            }
        }
    }
}

