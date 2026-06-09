package br.com.fiap.orbitagrid.ui.screens.monitor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AltRoute
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Satellite
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.com.fiap.orbitagrid.ui.components.StatCard
import br.com.fiap.orbitagrid.ui.components.ZoneCard
import br.com.fiap.orbitagrid.ui.navigation.Screen
import br.com.fiap.orbitagrid.ui.theme.DeepSpace
import br.com.fiap.orbitagrid.ui.theme.OrbitalCyan
import br.com.fiap.orbitagrid.ui.theme.OrbitalGreen
import br.com.fiap.orbitagrid.ui.theme.RiskCritical
import br.com.fiap.orbitagrid.ui.theme.SpaceCard
import br.com.fiap.orbitagrid.ui.theme.SpaceSurface
import br.com.fiap.orbitagrid.ui.theme.TextPrimary
import br.com.fiap.orbitagrid.ui.theme.TextSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MonitorScreen(
    navController: NavController,
    viewModel: MonitorViewModel = viewModel()
) {
    val selectedCity by viewModel.selectedCity.collectAsState()
    val zones by viewModel.zones.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Satellite, contentDescription = null, tint = OrbitalCyan, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Orbita Grid", fontWeight = FontWeight.Bold, color = TextPrimary, fontSize = 18.sp)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("• ORBITA GRID", fontSize = 11.sp, color = OrbitalCyan)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SpaceSurface)
            )
        },
        containerColor = DeepSpace
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text("Monitoramento Urbano", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                Text("Análise preditiva de colapso por satélite", fontSize = 13.sp, color = TextSecondary)
                Spacer(modifier = Modifier.height(4.dp))
            }

            item {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    StatCard(
                        label = "Satélites", value = viewModel.activeSatellites.toString(),
                        icon = Icons.Default.Satellite, tint = OrbitalCyan, modifier = Modifier.weight(1f)
                    )
                    StatCard(
                        label = "Zonas", value = viewModel.totalZones.toString(),
                        icon = Icons.Default.AltRoute, tint = OrbitalGreen, modifier = Modifier.weight(1f)
                    )
                    StatCard(
                        label = "Críticas", value = viewModel.criticalZones.toString(),
                        icon = Icons.Default.Warning, tint = RiskCritical, modifier = Modifier.weight(1f)
                    )
                }
            }

            item {
                Text("Selecionar Cidade", fontSize = 13.sp, color = TextSecondary, fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.height(8.dp))
                // Row simples (3 cidades cabem sem scroll)
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    viewModel.cities.forEach { city ->
                        val isSelected = city == selectedCity
                        FilterChip(
                            selected = isSelected,
                            onClick = { viewModel.selectCity(city) },
                            label = { Text(city, fontSize = 12.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = OrbitalCyan,
                                selectedLabelColor = DeepSpace,
                                containerColor = SpaceCard,
                                labelColor = TextSecondary
                            )
                        )
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Zonas de Risco — $selectedCity", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
                    Text("${zones.size} zonas", fontSize = 12.sp, color = TextSecondary)
                }
            }

            items(zones, key = { it.id }) { zone ->
                ZoneCard(zone = zone)
            }

            item {
                Spacer(modifier = Modifier.height(4.dp))
                Button(
                    onClick = { navController.navigate(Screen.Routes.route) },
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = OrbitalCyan)
                ) {
                    Text("Analisar Rotas com IA", fontWeight = FontWeight.Bold, color = DeepSpace)
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(Icons.Default.ArrowForward, contentDescription = null, tint = DeepSpace, modifier = Modifier.size(18.dp))
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}
