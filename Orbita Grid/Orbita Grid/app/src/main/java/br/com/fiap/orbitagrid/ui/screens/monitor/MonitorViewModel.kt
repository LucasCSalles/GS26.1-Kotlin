package br.com.fiap.orbitagrid.ui.screens.monitor

import androidx.lifecycle.ViewModel
import br.com.fiap.orbitagrid.data.model.UrbanZone
import br.com.fiap.orbitagrid.data.repository.OrbitaGridRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MonitorViewModel : ViewModel() {

    val cities = OrbitaGridRepository.getCities()
    val activeSatellites = OrbitaGridRepository.getActiveSatellitesCount()
    val criticalZones = OrbitaGridRepository.getCriticalZonesCount()
    val totalZones = OrbitaGridRepository.getAllZones().size

    private val _selectedCity = MutableStateFlow(cities.first())
    val selectedCity: StateFlow<String> = _selectedCity.asStateFlow()

    private val _zones = MutableStateFlow(OrbitaGridRepository.getZonesByCity(cities.first()))
    val zones: StateFlow<List<UrbanZone>> = _zones.asStateFlow()

    fun selectCity(city: String) {
        _selectedCity.value = city
        _zones.value = OrbitaGridRepository.getZonesByCity(city)
    }
}
