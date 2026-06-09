package br.com.fiap.orbitagrid.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Satellite
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.orbitagrid.data.repository.OrbitaGridRepository
import br.com.fiap.orbitagrid.ui.components.toColor
import br.com.fiap.orbitagrid.ui.components.toStabilityColor
import br.com.fiap.orbitagrid.ui.theme.DeepSpace
import br.com.fiap.orbitagrid.ui.theme.OrbitalCyan
import br.com.fiap.orbitagrid.ui.theme.OrbitalGreen
import br.com.fiap.orbitagrid.ui.theme.OrbitalPurple
import br.com.fiap.orbitagrid.ui.theme.RiskCritical
import br.com.fiap.orbitagrid.ui.theme.RiskHigh
import br.com.fiap.orbitagrid.ui.theme.RiskLow
import br.com.fiap.orbitagrid.ui.theme.RiskMedium
import br.com.fiap.orbitagrid.ui.theme.SpaceCard
import br.com.fiap.orbitagrid.ui.theme.SpaceCardBorder
import br.com.fiap.orbitagrid.ui.theme.SpaceSurface
import br.com.fiap.orbitagrid.ui.theme.TextMuted
import br.com.fiap.orbitagrid.ui.theme.TextPrimary
import br.com.fiap.orbitagrid.ui.theme.TextSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RouteDetailScreen(routeId: String, navController: NavController) {
    val route = OrbitaGridRepository.getRouteById(routeId)

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar", tint = TextPrimary)
                    }
                },
                title = { Text("Análise Preditiva", fontWeight = FontWeight.Bold, color = TextPrimary, fontSize = 18.sp) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SpaceSurface)
            )
        },
        containerColor = DeepSpace
    ) { paddingValues ->
        if (route == null) {
            Box(modifier = Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center) {
                Text("Rota não encontrada", color = TextSecondary)
            }
            return@Scaffold
        }

        val congestionColor = when {
            route.congestionProbability >= 70 -> RiskCritical
            route.congestionProbability >= 50 -> RiskHigh
            route.congestionProbability >= 30 -> RiskMedium
            else -> RiskLow
        }
        val stabilityColor = route.stabilityScore.toStabilityColor()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = SpaceCard)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "${route.origin}  →  ${route.destination}",
                                fontSize = 11.sp, color = OrbitalCyan, fontWeight = FontWeight.Medium
                            )
                            Text(text = route.name, fontSize = 21.sp, fontWeight = FontWeight.ExtraBold, color = TextPrimary)
                        }
                        if (route.isRecommended) {
                            Surface(shape = RoundedCornerShape(10.dp), color = OrbitalGreen.copy(alpha = 0.15f)) {
                                Column(
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Icon(Icons.Default.CheckCircle, contentDescription = null, tint = OrbitalGreen, modifier = Modifier.size(18.dp))
                                    Text("IA recomenda", fontSize = 9.sp, color = OrbitalGreen, fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        MetricPill("${route.estimatedMinutes} min", Icons.Default.Schedule, OrbitalCyan, Modifier.weight(1f))
                        MetricPill("${route.congestionProbability}% risco", Icons.Default.Speed, congestionColor, Modifier.weight(1f))
                        MetricPill("${route.stabilityScore}% estável", Icons.Default.CheckCircle, stabilityColor, Modifier.weight(1f))
                    }
                }
            }

            // Recomendação ORBITA GRID
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = OrbitalPurple.copy(alpha = 0.12f))
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Satellite, contentDescription = null, tint = OrbitalCyan, modifier = Modifier.size(15.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("RECOMENDAÇÃO ORBITA GRID", fontSize = 10.sp, color = OrbitalCyan, fontWeight = FontWeight.ExtraBold, letterSpacing = 0.8.sp)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = route.orbitalRecommendation, fontSize = 14.sp, color = TextPrimary, lineHeight = 22.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Schedule, contentDescription = null, tint = TextMuted, modifier = Modifier.size(12.dp))
                        Spacer(modifier = Modifier.width(5.dp))
                        Text("${route.updatedAt}  •  ${route.satelliteSource}", fontSize = 11.sp, color = TextMuted)
                    }
                }
            }

            // Fatores preditivos
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = SpaceCard)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text("FATORES PREDITIVOS", fontSize = 10.sp, color = OrbitalCyan, fontWeight = FontWeight.ExtraBold, letterSpacing = 1.sp)
                    Spacer(modifier = Modifier.height(14.dp))
                    route.predictiveFactors.forEachIndexed { index, factor ->
                        val factorColor = factor.riskLevel.toColor()
                        val icon: ImageVector = when (factor.name) {
                            "Tráfego" -> Icons.Default.Speed
                            "Clima" -> Icons.Default.Cloud
                            "Alagamento" -> Icons.Default.WaterDrop
                            else -> Icons.Default.LocalShipping
                        }
                        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                            Icon(icon, contentDescription = null, tint = factorColor, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(10.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                    Text(factor.name, fontSize = 13.sp, color = TextPrimary, fontWeight = FontWeight.Medium)
                                    Text("${factor.value}%", fontSize = 13.sp, color = factorColor, fontWeight = FontWeight.Bold)
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                LinearProgressIndicator(
                                    progress = factor.value / 100f,
                                    modifier = Modifier.fillMaxWidth().height(6.dp),
                                    color = factorColor,
                                    trackColor = SpaceCardBorder
                                )
                            }
                        }
                        if (index < route.predictiveFactors.lastIndex) {
                            Spacer(modifier = Modifier.height(12.dp))
                            HorizontalDivider(color = SpaceCardBorder, thickness = 0.5.dp)
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun MetricPill(text: String, icon: ImageVector, tint: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.background(tint.copy(alpha = 0.1f), RoundedCornerShape(10.dp)).padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = icon, contentDescription = null, tint = tint, modifier = Modifier.size(13.dp))
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = text, fontSize = 11.sp, color = tint, fontWeight = FontWeight.Bold)
        }
    }
}
