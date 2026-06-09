package br.com.fiap.orbitagrid.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.orbitagrid.data.model.RouteOption
import br.com.fiap.orbitagrid.ui.theme.OrbitalCyan
import br.com.fiap.orbitagrid.ui.theme.OrbitalGreen
import br.com.fiap.orbitagrid.ui.theme.SpaceCard
import br.com.fiap.orbitagrid.ui.theme.SpaceCardBorder
import br.com.fiap.orbitagrid.ui.theme.TextPrimary
import br.com.fiap.orbitagrid.ui.theme.TextSecondary

@Composable
fun RouteCard(
    route: RouteOption,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val congestionColor = route.congestionProbability.toRiskLevel().toColor()
    val stabilityColor = route.stabilityScore.toStabilityColor()

    Card(
        modifier = modifier.fillMaxWidth().clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SpaceCard),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                    if (route.isRecommended) {
                        Icon(Icons.Default.CheckCircle, contentDescription = null, tint = OrbitalGreen, modifier = Modifier.size(17.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                    }
                    Text(text = route.name, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                }
                if (route.isRecommended) {
                    Surface(shape = RoundedCornerShape(8.dp), color = OrbitalGreen.copy(alpha = 0.15f)) {
                        Text(
                            text = "IA recomenda", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = OrbitalGreen,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                MetricBox("${route.estimatedMinutes} min", Icons.Default.Schedule, OrbitalCyan, modifier = Modifier.weight(1f))
                MetricBox("${route.congestionProbability}% risco", Icons.Default.TrendingUp, congestionColor, modifier = Modifier.weight(1f))
                MetricBox("${route.stabilityScore}% estável", Icons.Default.ArrowForward, stabilityColor, modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(text = "Risco", fontSize = 11.sp, color = TextSecondary)
                LinearProgressIndicator(
                    progress = route.congestionProbability / 100f,
                    modifier = Modifier.weight(1f).height(5.dp),
                    color = congestionColor,
                    trackColor = SpaceCardBorder
                )
                Text(
                    text = route.congestionProbability.toRiskLevel().displayName,
                    fontSize = 11.sp, color = congestionColor, fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun MetricBox(value: String, icon: ImageVector, tint: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.background(tint.copy(alpha = 0.08f), RoundedCornerShape(10.dp)).padding(horizontal = 8.dp, vertical = 7.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(imageVector = icon, contentDescription = null, tint = tint, modifier = Modifier.size(13.dp))
            Spacer(modifier = Modifier.height(3.dp))
            Text(text = value, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = tint)
        }
    }
}
