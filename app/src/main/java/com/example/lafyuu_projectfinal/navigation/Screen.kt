package com.example.lafyuu_projectfinal.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object Explore : Screen("explore")
    object Cart : Screen("cart")
    object Offer : Screen("offer")
    object Account : Screen("account")
    object ProductDetail : Screen("product/{productId}") {
        fun createRoute(productId: Int) = "product/$productId"
    }
}