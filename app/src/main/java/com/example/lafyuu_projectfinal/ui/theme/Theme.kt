package com.example.lafyuu_projectfinal.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = BlueFF,
    secondary = Blue63,
    tertiary = GrayB1,
    background = Color.White
)

@Composable
fun Lafyuu_ProjectFinalTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}



