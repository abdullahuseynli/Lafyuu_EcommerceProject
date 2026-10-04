package com.example.lafyuu_projectfinal.screen.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lafyuu_projectfinal.R
import com.example.lafyuu_projectfinal.ui.theme.Blue63
import kotlinx.coroutines.delay

@Composable
fun FlashSaleBanner(modifier: Modifier = Modifier) {
    var secondsLeft by remember { mutableLongStateOf(8 * 3600L + 34 * 60 + 52) }

    LaunchedEffect(Unit) {
        while (secondsLeft > 0) {
            delay(1000)
            secondsLeft--
        }
    }

    val hours = secondsLeft / 3600
    val minutes = (secondsLeft % 3600) / 60
    val seconds = secondsLeft % 60

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(206.dp)
            .clip(RoundedCornerShape(5.dp))
    ) {
        Image(
            painter = painterResource(R.drawable.banner_flash_sale),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Black.copy(alpha = 0.35f))
        )

        Column(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 16.dp)
        ) {
            Text(
                text = "Super Flash Sale\n50% Off",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 32.sp
            )
            Spacer(Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                TimeBox(hours)
                Colon()
                TimeBox(minutes)
                Colon()
                TimeBox(seconds)
            }
        }
    }
}

@Composable
private fun TimeBox(value: Long) {
    Box(
        modifier = Modifier
            .size(width = 36.dp, height = 36.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "%02d".format(value),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Blue63
        )
    }
}

@Composable
private fun Colon() {
    Text(
        text = ":",
        color = Color.White,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(horizontal = 4.dp)
    )
}