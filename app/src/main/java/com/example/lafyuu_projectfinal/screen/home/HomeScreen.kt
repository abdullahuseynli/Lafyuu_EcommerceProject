package com.example.lafyuu_projectfinal.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.lafyuu_projectfinal.screen.components.ProductCard
import com.example.lafyuu_projectfinal.screen.components.productGrid
import com.example.lafyuu_projectfinal.screen.home.components.CategorySection
import com.example.lafyuu_projectfinal.screen.home.components.FlashSaleBanner
import com.example.lafyuu_projectfinal.screen.home.components.HomeSearchBar
import com.example.lafyuu_projectfinal.screen.home.components.ProductRowSection
import com.example.lafyuu_projectfinal.screen.home.components.RecommendedBanner

@Composable
fun HomeScreen(
    onSeeMoreClick: () -> Unit,
    onProductClick: (Int) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
    ) {
        HomeSearchBar(
            onSearchClick = { },
            onFavoriteClick = { },
            onNotificationClick = { },
            modifier = Modifier.padding(start = 16.dp, end = 8.dp, top = 8.dp)
        )

        Box(modifier = Modifier.fillMaxSize()) {
            when {
                state.isLoading ->
                    CircularProgressIndicator(Modifier.align(Alignment.Center))

                state.error != null -> Column(
                    Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Xəta: ${state.error}")
                    Spacer(Modifier.height(8.dp))
                    Button(onClick = { viewModel.loadHome() }) { Text("Yenidən cəhd et") }
                }

                else -> LazyColumn(contentPadding = PaddingValues(16.dp)) {
                    item { FlashSaleBanner() }

                    item {
                        Spacer(Modifier.height(24.dp))
                        CategorySection(
                            categories = state.categories,
                            onCategoryClick = { },
                            onMoreClick = { }
                        )
                    }

                    item {
                        Spacer(Modifier.height(24.dp))
                        ProductRowSection(
                            title = "Flash Sale",
                            products = state.flashSale,
                            onSeeMoreClick = onSeeMoreClick,
                            onProductClick = onProductClick
                        )
                    }

                    item {
                        Spacer(Modifier.height(24.dp))
                        RecommendedBanner()
                        Spacer(Modifier.height(16.dp))
                    }

                    productGrid(
                        products = state.recommended,
                        onProductClick = onProductClick,
                        showRating = true
                    )
                }
            }

        }
    }
}
