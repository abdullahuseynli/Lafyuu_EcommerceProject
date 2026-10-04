package com.example.lafyuu_projectfinal.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lafyuu_projectfinal.navigation.Screen
import com.example.lafyuu_projectfinal.navigation.bottomNavItems
import com.example.lafyuu_projectfinal.ui.theme.BlueFF
import com.example.lafyuu_projectfinal.ui.theme.GrayB1
import com.example.lafyuu_projectfinal.ui.theme.RedFB

@Composable
fun BottomNavBar(
    currentRoute: String?,
    onItemClick: (Screen) -> Unit,
    cartCount: Int = 0
) {
    NavigationBar(containerColor = Color.White) {
        bottomNavItems.forEach { item ->
            val selected = currentRoute == item.screen.route

            NavigationBarItem(
                selected = selected,
                onClick = { onItemClick(item.screen) },
                icon = {
                    if (item.screen == Screen.Cart && cartCount > 0) {
                        BadgedBox(
                            badge = {
                                Badge(containerColor = RedFB, contentColor = Color.White) {
                                    Text(cartCount.toString(), fontSize = 8.sp)
                                }
                            }
                        ) {
                            Icon(painterResource(item.icon), contentDescription = item.label)
                        }
                    } else {
                        Icon(painterResource(item.icon), contentDescription = item.label)
                    }
                },
                label = { Text(item.label, fontSize = 10.sp) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = BlueFF,
                    selectedTextColor = BlueFF,
                    unselectedIconColor = GrayB1,
                    unselectedTextColor = GrayB1,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}