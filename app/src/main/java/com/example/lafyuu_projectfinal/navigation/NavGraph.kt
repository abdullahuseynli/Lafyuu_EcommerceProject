package com.example.lafyuu_projectfinal.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lafyuu_projectfinal.screen.components.BottomNavBar
import com.example.lafyuu_projectfinal.screen.components.PlaceholderScreen
import com.example.lafyuu_projectfinal.screen.detail.ProductDetailScreen
import com.example.lafyuu_projectfinal.screen.home.HomeScreen
import com.example.lafyuu_projectfinal.screen.login.LoginScreen
import com.example.lafyuu_projectfinal.screen.offer.OfferScreen
import com.example.lafyuu_projectfinal.screen.register.RegisterScreen
import com.example.lafyuu_projectfinal.screen.splash.SplashScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    // Panel yalnız əsas 5 ekranda görünür
    val showBottomBar = bottomNavItems.any { it.screen.route == currentRoute }

    val navigateToTab: (Screen) -> Unit = { screen ->
        navController.navigate(screen.route) {
            popUpTo(Screen.Home.route) { saveState = true }
            launchSingleTop = true
            restoreState = true
        }
    }

    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            if (showBottomBar) {
                BottomNavBar(
                    currentRoute = currentRoute,
                    cartCount = 2,
                    onItemClick = navigateToTab
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Splash.route) {
                SplashScreen(
                    onFinished = {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Splash.route) { inclusive = true }
                        }
                    }
                )
            }
            composable(Screen.Login.route) {
                LoginScreen(
                    onSignInSuccess = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    },
                    onRegisterClick = { navController.navigate(Screen.Register.route) }
                )
            }
            composable(Screen.Register.route) {
                RegisterScreen(
                    onSignUpSuccess = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    },
                    onSignInClick = { navController.popBackStack() }
                )
            }

            composable(Screen.Home.route) {
                HomeScreen(
                    onSeeMoreClick = { navigateToTab(Screen.Offer) },
                    onProductClick = { id ->
                        navController.navigate(Screen.ProductDetail.createRoute(id))
                    }
                )
            }
            composable(Screen.Offer.route) {
                OfferScreen(
                    onBackClick = { navController.popBackStack() },
                    onSearchClick = { },
                    onProductClick = { id ->
                        navController.navigate(Screen.ProductDetail.createRoute(id))
                    }
                )
            }
            composable(Screen.Explore.route) { PlaceholderScreen("Explore") }
            composable(Screen.Cart.route) { PlaceholderScreen("Cart") }
            composable(Screen.Account.route) { PlaceholderScreen("Account") }

            composable(
                route = Screen.ProductDetail.route,
                arguments = listOf(navArgument("productId") { type = NavType.IntType })
            ) {
                ProductDetailScreen(
                    onBackClick = { navController.popBackStack() },
                    onSearchClick = { },
                    onProductClick = { id ->
                        navController.navigate(Screen.ProductDetail.createRoute(id))
                    }
                )
            }
        }
    }
}