package int20h.troipsa.demoapp.ui.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import int20h.troipsa.demoapp.R
import int20h.troipsa.demoapp.ui.screens.map.MapScreenContent
import int20h.troipsa.demoapp.ui.screens.schedule.ScheduleScreenContent
import int20h.troipsa.demoapp.ui.screens.legal.LegalScreenContent

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalLayoutApi::class,
    ExperimentalAnimationApi::class
)
@Composable
fun DemoAppNavHost() {
    val navController = rememberAnimatedNavController()

    val bottomNavItems = listOf(
        Screen.MapScreen,
        Screen.ScheduleScreen,
        Screen.LegalScreen,
    )

    var bottomNavigationVisible by remember { mutableStateOf(true) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            AnimatedVisibility(visible = bottomNavigationVisible) {
                DemoAppBottomNavigation(
                    navController = navController,
                    bottomNavItems = bottomNavItems
                )
            }
        },
    ) { innerPadding ->
        DemoAppNavigation(
            navController = navController,
            showBottomSheet = { show -> bottomNavigationVisible = show },
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .consumeWindowInsets(paddingValues = innerPadding)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal,
                    ),
                ),
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun DemoAppNavigation(
    navController: NavHostController,
    showBottomSheet: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.MapScreen.route,
        modifier = modifier
    ) {

        horizontallyAnimatedComposable(Screen.MapScreen.route,) {
            MapScreenContent(
                showBottomSheet = showBottomSheet
               /* navigateToScreen2 = { arg ->
                    navController.navigate(Screen.ScheduleScreen.withArgs(arg.toString()))
                },
                navigateToScreen3 = { arg ->
                    navController.navigate(Screen.Screen3.withArgs(arg.toString()))
                }*/
            )
        }
        horizontallyAnimatedComposable(
             route = Screen.ScheduleScreen.route,
             directionRight = { initialState.destination.route != Screen.LegalScreen.route }
        ) {
            ScheduleScreenContent(
                popBackStack = { navController.popBackStack() }
            )
        }
        horizontallyAnimatedComposable(
            route = Screen.LegalScreen.route,
            directionRight = { targetState.destination.route != Screen.ScheduleScreen.route }
        ) {
            LegalScreenContent()
        }
       /* verticallyAnimatedComposable(
            route = Screen.Screen3.withArgsFormat(Screen.Screen3.demoArgument),
            arguments = listOf(
                navArgument(Screen.Screen3.demoArgument) { type = NavType.IntType }
            )
        ) { navBackStackEntry ->
            LegalScreenContent(
                argumentFromScreen1 = navBackStackEntry.arguments?.getInt(Screen.Screen3.demoArgument),
                popBackStack = { navController.popBackStack() }
            )
        }*/
    }
}

@Composable
private fun DemoAppBottomNavigation(
    navController: NavHostController,
    bottomNavItems: List<Screen>
) {
    NavigationBar(
        contentColor = DemoAppNavigationDefaults.navigationContentColor(),
        tonalElevation = 0.dp,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        bottomNavItems.forEach { screen ->
            NavigationBarItem(
                selected = currentDestination
                    ?.hierarchy
                    ?.any { it.route == screen.route } == true,
                icon = {
                    Icon(
                        painter = painterResource(
                            id = screen.iconRes ?: R.drawable.ic_calendar
                        ),
                        contentDescription = null
                    )
                },
                label = { Text(text = stringResource(id = screen.resourceId)) },
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}