package br.com.fiap.orbitagrid.ui.screens.routes

import androidx.lifecycle.ViewModel
import br.com.fiap.orbitagrid.data.model.RouteOption
import br.com.fiap.orbitagrid.data.model.RouteType
import br.com.fiap.orbitagrid.data.repository.OrbitaGridRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RoutesViewModel : ViewModel() {

    val routeTypes = RouteType.values().toList()

    private val _selectedType = MutableStateFlow(RouteType.ALL)
    val selectedType: StateFlow<RouteType> = _selectedType.asStateFlow()

    private val _routes = MutableStateFlow(OrbitaGridRepository.getAllRoutes())
    val routes: StateFlow<List<RouteOption>> = _routes.asStateFlow()

    fun filterByType(type: RouteType) {
        _selectedType.value = type
        _routes.value = OrbitaGridRepository.getRoutesByType(type)
    }
}
