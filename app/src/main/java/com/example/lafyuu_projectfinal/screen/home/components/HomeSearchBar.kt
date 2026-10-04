package com.example.lafyuu_projectfinal.screen.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lafyuu_projectfinal.R
import com.example.lafyuu_projectfinal.ui.theme.BlueFF
import com.example.lafyuu_projectfinal.ui.theme.GrayB1
import com.example.lafyuu_projectfinal.ui.theme.GreyFF
import com.example.lafyuu_projectfinal.ui.theme.RedFB

@Composable
fun HomeSearchBar(
    onSearchClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    onNotificationClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .height(46.dp)
                .border(1.dp, GreyFF, RoundedCornerShape(5.dp))
                .clickable { onSearchClick() }
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_search),
                contentDescription = null,
                tint = BlueFF,
                modifier = Modifier.size(20.dp)
            )
            Spacer(Modifier.width(12.dp))
            Text("Search Product", fontSize = 12.sp, color = GrayB1)
        }

        Spacer(Modifier.width(4.dp))

        IconButton(onClick = onFavoriteClick) {
            Icon(
                painter = painterResource(R.drawable.ic_love),
                contentDescription = "Favorites",
                tint = GrayB1
            )
        }

        IconButton(onClick = onNotificationClick) {
            Box {
                Icon(
                    painter = painterResource(R.drawable.ic_notification),
                    contentDescription = "Notifications",
                    tint = GrayB1
                )
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(RedFB, CircleShape)
                        .align(Alignment.TopEnd)
                )
            }
        }
    }
}