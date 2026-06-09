package br.com.fiap.orbitagrid.ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.fiap.orbitagrid.ui.screens.detail.RouteDetailScreen
import br.com.fiap.orbitagrid.ui.screens.home.HomeScreen
import br.com.fiap.orbitagrid.ui.screens.monitor.MonitorScreen
import br.com.fiap.orbitagrid.ui.screens.routes.RoutesScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.Monitor.route) {
            MonitorScreen(navController = navController)
        }
        composable(Screen.Routes.route) {
            RoutesScreen(navController = navController)
        }
        composable(
            route = Screen.RouteDetail.route,
            arguments = listOf(navArgument("routeId") { type = NavType.StringType })
        ) { backStackEntry ->
            RouteDetailScreen(
                routeId = backStackEntry.arguments?.getString("routeId") ?: "",
                navController = navController
            )
        }
    }
}
