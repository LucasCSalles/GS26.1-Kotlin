package br.com.fiap.orbitagrid.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Monitor : Screen("monitor")
    object Routes : Screen("routes")
    object RouteDetail : Screen("route_detail/{routeId}") {
        fun createRoute(routeId: String) = "route_detail/$routeId"
    }
}
