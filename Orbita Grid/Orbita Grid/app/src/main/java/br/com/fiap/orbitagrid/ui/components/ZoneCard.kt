package br.com.fiap.orbitagrid.ui.components

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Satellite
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.orbitagrid.data.model.UrbanZone
import br.com.fiap.orbitagrid.ui.theme.SpaceCard
import br.com.fiap.orbitagrid.ui.theme.SpaceCardBorder
import br.com.fiap.orbitagrid.ui.theme.TextPrimary
import br.com.fiap.orbitagrid.ui.theme.TextSecondary

@Composable
fun ZoneCard(
    zone: UrbanZone,
    modifier: Modifier = Modifier
) {
    val riskColor = zone.riskLevel.toColor()

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SpaceCard),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.LocationOn, contentDescription = null, tint = riskColor, modifier = Modifier.size(13.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = zone.city, fontSize = 11.sp, color = riskColor, fontWeight = FontWeight.Bold)
                    }
                    Text(text = zone.name, fontSize = 17.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(text = "${zone.collapseChance}%", fontSize = 28.sp, fontWeight = FontWeight.ExtraBold, color = riskColor)
                    Text(text = "colapso", fontSize = 10.sp, color = TextSecondary)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            LinearProgressIndicator(
                progress = zone.collapseChance / 100f,
                modifier = Modifier.fillMaxWidth().height(6.dp),
                color = riskColor,
                trackColor = SpaceCardBorder
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Schedule, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(13.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "colapso em ~${zone.collapseInMinutes} min", fontSize = 12.sp, color = TextSecondary)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Satellite, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(12.dp))
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(text = zone.satelliteSource, fontSize = 11.sp, color = TextSecondary)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                zone.factors.take(2).forEach { factor ->
                    Surface(shape = RoundedCornerShape(6.dp), color = riskColor.copy(alpha = 0.12f)) {
                        Text(
                            text = factor, fontSize = 10.sp, color = riskColor, fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }
                }
                if (zone.factors.size > 2) {
                    Surface(shape = RoundedCornerShape(6.dp), color = SpaceCardBorder) {
                        Text(
                            text = "+${zone.factors.size - 2}", fontSize = 10.sp, color = TextSecondary,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }
                }
            }
        }
    }
}
