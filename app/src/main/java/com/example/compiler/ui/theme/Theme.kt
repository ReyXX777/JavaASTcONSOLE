package com.example.compiler.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = PrimaryLight,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFD6E3FF),
    onPrimaryContainer = Color(0xFF001C3A),
    secondary = SecondaryLight,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFE0E0FF),
    onSecondaryContainer = Color(0xFF191C37),
    background = BackgroundLight,
    onBackground = Color(0xFF1A1C1E),
    surface = SurfaceLight,
    onSurface = Color(0xFF1A1C1E),
    error = ErrorLight,
    onError = Color.White,
    errorContainer = Color(0xFFF9DEDC),
    onErrorContainer = Color(0xFF410E0B)
)

private val DarkColors = darkColorScheme(
    primary = PrimaryDark,
    onPrimary = Color(0xFF0B1A2F),
    primaryContainer = Color(0xFF00497D),
    onPrimaryContainer = Color(0xFF0B1A2F),
    secondary = SecondaryDark,
    onSecondary = Color(0xFF2E3152),
    secondaryContainer = Color(0xFF45496A),
    onSecondaryContainer = Color(0xFFE0E0FF),
    background = BackgroundDark,
    onBackground = Color(0xFFE2E2E5),
    surface = SurfaceDark,
    onSurface = Color(0xFFE2E2E5),
    error = ErrorDark,
    onError = Color(0xFF601410),
    errorContainer = Color(0xFF8C1D18),
    onErrorContainer = Color(0xFFF9DEDC)
)

@Composable
fun YourAppTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}