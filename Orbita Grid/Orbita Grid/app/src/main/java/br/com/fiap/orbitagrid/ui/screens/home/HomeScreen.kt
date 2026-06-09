package br.com.fiap.orbitagrid.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.AltRoute
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material.icons.filled.Satellite
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.orbitagrid.ui.navigation.Screen
import br.com.fiap.orbitagrid.ui.theme.DeepSpace
import br.com.fiap.orbitagrid.ui.theme.OrbitalCyan
import br.com.fiap.orbitagrid.ui.theme.OrbitalGreen
import br.com.fiap.orbitagrid.ui.theme.OrbitalPurple
import br.com.fiap.orbitagrid.ui.theme.TextMuted
import br.com.fiap.orbitagrid.ui.theme.TextPrimary
import br.com.fiap.orbitagrid.ui.theme.TextSecondary

@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF040810), DeepSpace, Color(0xFF0A1628))
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .padding(top = 48.dp, bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Badge
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = OrbitalGreen.copy(alpha = 0.12f),
                modifier = Modifier.border(1.dp, OrbitalGreen.copy(alpha = 0.4f), RoundedCornerShape(8.dp))
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.size(6.dp).background(OrbitalGreen, CircleShape))
                    Spacer(modifier = Modifier.width(7.dp))
                    Text(
                        text = "ORBITA GRID • SISTEMA CONECTADO",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = OrbitalGreen,
                        letterSpacing = 0.8.sp
                    )
                }
            }

            // Logo + Title
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(contentAlignment = Alignment.Center) {
                    Box(modifier = Modifier.size(96.dp).border(1.dp, OrbitalCyan.copy(alpha = 0.15f), CircleShape))
                    Box(modifier = Modifier.size(70.dp).border(1.dp, OrbitalCyan.copy(alpha = 0.3f), CircleShape))
                    Box(
                        modifier = Modifier.size(46.dp).background(OrbitalCyan.copy(alpha = 0.12f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Satellite, contentDescription = null, tint = OrbitalCyan, modifier = Modifier.size(24.dp))
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text("Orbita Grid", fontSize = 38.sp, fontWeight = FontWeight.ExtraBold, color = TextPrimary, letterSpacing = (-1.5).sp)
                Text("powered by ORBITA GRID", fontSize = 11.sp, color = OrbitalCyan, fontWeight = FontWeight.Medium, letterSpacing = 0.8.sp)
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Preveja o colapso urbano antes que ele aconteça",
                    fontSize = 13.sp, color = TextSecondary, textAlign = TextAlign.Center
                )
            }

            // Features
            Column(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
                CompactFeatureRow(Icons.Default.Analytics, "IA Preditiva", "Satélites preveem colapsos com 30–90 min de antecedência", OrbitalCyan)
                CompactFeatureRow(Icons.Default.AltRoute, "Rotas Inteligentes", "Compare probabilidade de congestionamento e estabilidade", OrbitalPurple)
                CompactFeatureRow(Icons.Default.Shield, "Logística Segura", "Rotas otimizadas para caminhoneiros e frotas pesadas", OrbitalGreen)
            }

            // Stats
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                HomeStatItem("4+", "Satélites")
                HomeStatItem("3", "Cidades")
                HomeStatItem("8", "Rotas")
            }

            // CTA + version
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Button(
                    onClick = { navController.navigate(Screen.Monitor.route) },
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = OrbitalCyan)
                ) {
                    Icon(Icons.Default.RocketLaunch, contentDescription = null, tint = DeepSpace, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Acessar Monitoramento", color = DeepSpace, fontWeight = FontWeight.ExtraBold, fontSize = 15.sp)
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text("v1.0.0  •  FIAP Global Solution 2026", fontSize = 10.sp, color = TextMuted)
            }
        }
    }
}

@Composable
private fun CompactFeatureRow(icon: ImageVector, title: String, description: String, tint: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier.size(34.dp).background(tint.copy(alpha = 0.15f), RoundedCornerShape(9.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = tint, modifier = Modifier.size(18.dp))
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
            Text(text = description, fontSize = 11.sp, color = TextSecondary, lineHeight = 16.sp)
        }
    }
}

@Composable
private fun HomeStatItem(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = value, fontSize = 26.sp, fontWeight = FontWeight.ExtraBold, color = OrbitalCyan)
        Text(text = label, fontSize = 11.sp, color = TextSecondary, fontWeight = FontWeight.Medium)
    }
}
