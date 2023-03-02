package int20h.troipsa.demoapp.ui.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import int20h.troipsa.demoapp.R
import int20h.troipsa.demoapp.ui.screens.screen_1.Screen1
import int20h.troipsa.demoapp.ui.screens.screen_2.Screen2

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun DemoAppNavHost() {
    val navController = rememberNavController()

    val bottomNavItems = listOf(
        Screen.Screen1,
        Screen.Screen2
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            //todo maybe will need to hide bottom bar on some screens
            if (true) {
                DemoAppBottomNavigation(
                    navController = navController,
                    bottomNavItems = bottomNavItems
                )
            }
        },
    ) { innerPadding ->
        DemoAppNavigation(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .consumedWindowInsets(paddingValues = innerPadding)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal,
                    ),
                ),
        )
    }
}

@Composable
private fun DemoAppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Screen1.route,
        modifier = modifier
    ) {
        composable(Screen.Screen1.route) {
            Screen1(
                navController = navController,
                navigateToScreen2 = { arg ->
                    navController.navigate(Screen.Screen2.withArgs(arg.toString()))
                },
            )
        }
        composable(
            route = Screen.Screen2.withArgsFormat(Screen.Screen2.arg),
            arguments = listOf(
                navArgument(Screen.Screen2.arg) { type = NavType.IntType }
            )
        ) { navBackStackEntry ->
            Screen2(
                argFromScreen1 = navBackStackEntry.arguments?.getInt(Screen.Screen2.arg),
                popBackStack = { navController.popBackStack() }
            )
        }
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