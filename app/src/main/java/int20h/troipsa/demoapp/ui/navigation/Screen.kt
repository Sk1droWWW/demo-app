package int20h.troipsa.demoapp.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import int20h.troipsa.demoapp.R

sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
    @DrawableRes val iconRes: Int? = null,
) {
    object MapScreen : Screen("map_screen", R.string.map_screen_title, R.drawable.ic_map)
    object ScheduleScreen : Screen("schedule_screen", R.string.schedule_screen_title, R.drawable.ic_schedule)
    object LegalScreen : Screen("legal_screen", R.string.legal_screen_title, R.drawable.ic_legal)

    object Screen3 : Screen("screen3", R.string.map_screen_title) {
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

