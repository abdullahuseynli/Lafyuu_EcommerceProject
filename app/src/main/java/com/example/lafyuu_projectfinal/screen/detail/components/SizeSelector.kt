package com.example.lafyuu_projectfinal.screen.detail.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lafyuu_projectfinal.ui.theme.Blue63
import com.example.lafyuu_projectfinal.ui.theme.BlueFF
import com.example.lafyuu_projectfinal.ui.theme.GreyFF

val shoeSizes = listOf("6", "6.5", "7", "7.5", "8", "8.5")

@Composable
fun SizeSelector(
    sizes: List<String>,
    selected: String,
    onSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text("Select Size", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Blue63)
        Spacer(Modifier.height(12.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(sizes) { size ->
                val isSelected = size == selected
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .border(1.dp, if (isSelected) BlueFF else GreyFF, CircleShape)
                        .clickable { onSelect(size) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = size,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isSelected) BlueFF else Blue63
                    )
                }
            }
        }
    }
}