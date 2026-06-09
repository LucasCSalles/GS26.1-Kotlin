package br.com.fiap.orbitagrid.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AltRoute
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import br.com.fiap.orbitagrid.data.model.RiskLevel
import br.com.fiap.orbitagrid.data.model.RouteType
import br.com.fiap.orbitagrid.ui.theme.OrbitalCyan
import br.com.fiap.orbitagrid.ui.theme.RiskCritical
import br.com.fiap.orbitagrid.ui.theme.RiskHigh
import br.com.fiap.orbitagrid.ui.theme.RiskLow
import br.com.fiap.orbitagrid.ui.theme.RiskMedium
import br.com.fiap.orbitagrid.ui.theme.RiskSafe

fun RiskLevel.toColor(): Color = when (this) {
    RiskLevel.CRITICAL -> RiskCritical
    RiskLevel.HIGH -> RiskHigh
    RiskLevel.MEDIUM -> RiskMedium
    RiskLevel.LOW -> RiskLow
    RiskLevel.SAFE -> RiskSafe
}

fun RiskLevel.toBackgroundColor(): Color = this.toColor().copy(alpha = 0.15f)

fun RouteType.toIcon(): ImageVector = when (this) {
    RouteType.ALL -> Icons.Default.Public
    RouteType.RECOMMENDED -> Icons.Default.AltRoute
    RouteType.HIGH_RISK -> Icons.Default.Warning
    RouteType.LOGISTICS -> Icons.Default.LocalShipping
}

fun Int.toRiskLevel(): RiskLevel = when {
    this >= 70 -> RiskLevel.CRITICAL
    this >= 50 -> RiskLevel.HIGH
    this >= 30 -> RiskLevel.MEDIUM
    this >= 10 -> RiskLevel.LOW
    else -> RiskLevel.SAFE
}

fun Int.toStabilityColor(): Color = when {
    this >= 75 -> RiskLow
    this >= 50 -> OrbitalCyan
    this >= 30 -> RiskMedium
    else -> RiskCritical
}
