package com.example.lafyuu_projectfinal.screen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.lafyuu_projectfinal.ui.theme.GreyFF
import com.example.lafyuu_projectfinal.ui.theme.YellowStar
import kotlin.math.roundToInt

@Composable
fun StarRating(
    rating: Double,
    modifier: Modifier = Modifier,
    starSize: Dp = 12.dp
) {
    val filled = rating.roundToInt().coerceIn(0, 5)
    Row(modifier = modifier) {
        repeat(5) { index ->
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = if (index < filled) YellowStar else GreyFF,
                modifier = Modifier.size(starSize)
            )
        }
    }
}