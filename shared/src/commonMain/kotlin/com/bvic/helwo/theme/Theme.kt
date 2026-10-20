package com.bvic.helwo.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

object HelwoColors {
    val PurplePrimary = Color(0xFF4F3BEA)
    val PurpleSecondary = Color(0xFF7B44EC)
    val CoralPrimary = Color(0xFFFF624F)
    val CoralLight = Color(0xFFFFEFEB)

    val BackgroundLight = Color(0xFFF4F5FA)
    val CardBackground = Color(0xFFFFFFFF)
    val DarkBottomBar = Color(0xFF1A1C29)

    val TextPrimary = Color(0xFF11142D)
    val TextSecondary = Color(0xFF7A809B)

    val StatusPayeBg = Color(0xFFE2F7EC)
    val StatusPayeText = Color(0xFF18B062)

    val StatusAPayerBg = Color(0xFFFFEBE8)
    val StatusAPayerText = Color(0xFFFF523B)

    val StatusEnAttenteBg = Color(0xFFFFEBE8)
    val StatusEnAttenteText = Color(0xFFFF523B)

    val GradientPurpleToCoral = Brush.linearGradient(
        colors = listOf(Color(0xFF5334EB), Color(0xFF8133E0), Color(0xFFFF5F4E))
    )

    val GradientPurpleSolid = Brush.linearGradient(
        colors = listOf(Color(0xFF4A34EB), Color(0xFF6333EB))
    )

    val GradientOrangeHeader = Brush.linearGradient(
        colors = listOf(Color(0xFFFF8D41), Color(0xFFFF5F4E))
    )
}

private val LightColorScheme = lightColorScheme(
    primary = HelwoColors.PurplePrimary,
    secondary = HelwoColors.CoralPrimary,
    background = HelwoColors.BackgroundLight,
    surface = HelwoColors.CardBackground,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = HelwoColors.TextPrimary,
    onSurface = HelwoColors.TextPrimary
)

@Composable
fun HelwoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        content = content
    )
}
