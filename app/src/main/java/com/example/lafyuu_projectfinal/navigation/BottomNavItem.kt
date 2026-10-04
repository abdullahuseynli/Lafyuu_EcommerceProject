package com.example.lafyuu_projectfinal.navigation

import androidx.annotation.DrawableRes
import com.example.lafyuu_projectfinal.R

data class BottomNavItem(
    val screen: Screen,
    val label: String,
    @DrawableRes val icon: Int
)

val bottomNavItems = listOf(
    BottomNavItem(Screen.Home, "Home", R.drawable.ic_nav_home),
    BottomNavItem(Screen.Explore, "Explore", R.drawable.ic_nav_explore),
    BottomNavItem(Screen.Cart, "Cart", R.drawable.ic_nav_cart),
    BottomNavItem(Screen.Offer, "Offer", R.drawable.ic_nav_offer),
    BottomNavItem(Screen.Account, "Account", R.drawable.ic_nav_account)
)