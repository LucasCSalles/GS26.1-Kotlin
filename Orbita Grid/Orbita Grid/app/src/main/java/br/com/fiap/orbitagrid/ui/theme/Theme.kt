package br.com.fiap.orbitagrid.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = OrbitalCyan,
    onPrimary = DeepSpace,
    primaryContainer = OrbitalCyanDark,
    onPrimaryContainer = TextPrimary,
    secondary = OrbitalPurple,
    onSecondary = TextPrimary,
    tertiary = OrbitalGreen,
    onTertiary = DeepSpace,
    background = DeepSpace,
    onBackground = TextPrimary,
    surface = SpaceSurface,
    onSurface = TextPrimary,
    surfaceVariant = SpaceCard,
    onSurfaceVariant = TextSecondary,
    error = RiskCritical,
    onError = TextPrimary,
    outline = SpaceCardBorder
)

@Composable
fun OrbitaGridTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}
