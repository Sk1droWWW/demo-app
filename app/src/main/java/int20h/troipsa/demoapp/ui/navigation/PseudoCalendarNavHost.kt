package int20h.troipsa.demoapp.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import int20h.troipsa.demoapp.ui.screens.screen_1.Screen1
import int20h.troipsa.demoapp.ui.screens.screen_2.Screen2
import int20h.troipsa.demoapp.R

@Composable
fun PseudoCalendarNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Screen1.route
) {
    val navController = rememberNavController()

    val bottomNavItems = listOf(
        Screen.Screen1,
//        Screen.Contacts
    )

    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                bottomNavItems.forEach { screen ->
                    BottomNavigationItem(
                        selected = currentDestination
                            ?.hierarchy
                            ?.any { it.route == screen.route } == true,
                        icon = {
                            Icon(
                                painter = painterResource(id = screen.iconRes ?: R.drawable.ic_calendar),
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(text = stringResource(id = screen.resourceId))
                        },
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
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Screen1.route,
            modifier = Modifier.padding(innerPadding)
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
}
