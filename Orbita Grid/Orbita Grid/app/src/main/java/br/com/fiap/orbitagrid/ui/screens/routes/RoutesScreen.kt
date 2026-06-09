package br.com.fiap.orbitagrid.ui.screens.routes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import br.com.fiap.orbitagrid.ui.components.RouteCard
import br.com.fiap.orbitagrid.ui.components.toIcon
import br.com.fiap.orbitagrid.ui.navigation.Screen
import br.com.fiap.orbitagrid.ui.theme.DeepSpace
import br.com.fiap.orbitagrid.ui.theme.OrbitalCyan
import br.com.fiap.orbitagrid.ui.theme.SpaceCard
import br.com.fiap.orbitagrid.ui.theme.SpaceSurface
import br.com.fiap.orbitagrid.ui.theme.TextPrimary
import br.com.fiap.orbitagrid.ui.theme.TextSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoutesScreen(
    navController: NavController,
    viewModel: RoutesViewModel = viewModel()
) {
    val routes by viewModel.routes.collectAsState()
    val selectedType by viewModel.selectedType.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar", tint = TextPrimary)
                    }
                },
                title = { Text("Análise de Rotas", fontWeight = FontWeight.Bold, color = TextPrimary, fontSize = 18.sp) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SpaceSurface)
            )
        },
        containerColor = DeepSpace
    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {

            // Header — origem/destino
            Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.LocationOn, contentDescription = null, tint = OrbitalCyan, modifier = Modifier.size(15.dp))
                    Spacer(modifier = Modifier.width(5.dp))
                    Text("Osasco", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(Icons.Default.ArrowForward, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(13.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(Icons.Default.LocationOn, contentDescription = null, tint = OrbitalCyan, modifier = Modifier.size(15.dp))
                    Spacer(modifier = Modifier.width(5.dp))
                    Text("Guarulhos", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                }
                Text("Previsão ORBITA GRID • Atualizado agora", fontSize = 11.sp, color = TextSecondary)
            }

            // Filtros de tipo
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(viewModel.routeTypes) { type ->
                    val isSelected = type == selectedType
                    FilterChip(
                        selected = isSelected,
                        onClick = { viewModel.filterByType(type) },
                        label = { Text(type.displayName, fontSize = 12.sp, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal) },
                        leadingIcon = { Icon(imageVector = type.toIcon(), contentDescription = null, modifier = Modifier.size(14.dp)) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = OrbitalCyan, selectedLabelColor = DeepSpace,
                            selectedLeadingIconColor = DeepSpace, containerColor = SpaceCard,
                            labelColor = TextSecondary, iconColor = TextSecondary
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Row(modifier = Modifier.padding(horizontal = 20.dp)) {
                Text("${routes.size} rota${if (routes.size != 1) "s" else ""}", fontSize = 12.sp, color = TextSecondary)
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Lista — weight(1f) garante que não cause crash por altura infinita
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(routes, key = { it.id }) { route ->
                    RouteCard(
                        route = route,
                        onClick = { navController.navigate(Screen.RouteDetail.createRoute(route.id)) }
                    )
                }
                item { Spacer(modifier = Modifier.height(16.dp)) }
            }
        }
    }
}
