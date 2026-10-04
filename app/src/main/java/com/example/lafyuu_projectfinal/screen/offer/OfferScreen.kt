package com.example.lafyuu_projectfinal.screen.offer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.lafyuu_projectfinal.R
import com.example.lafyuu_projectfinal.screen.components.productGrid
import com.example.lafyuu_projectfinal.screen.home.components.FlashSaleBanner
import com.example.lafyuu_projectfinal.ui.theme.Blue63
import com.example.lafyuu_projectfinal.ui.theme.GrayB1

@Composable
fun OfferScreen(
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit,
    onProductClick: (Int) -> Unit,
    viewModel: OfferViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
    ) {
        // Üst panel
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = GrayB1
                )
            }
            Text(
                text = "Super Flash Sale",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Blue63,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = onSearchClick) {
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = "Search",
                    tint = GrayB1
                )
            }
        }

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
                    Button(onClick = { viewModel.load() }) { Text("Yenidən cəhd et") }
                }

                else -> LazyColumn(contentPadding = PaddingValues(16.dp)) {
                    item {
                        FlashSaleBanner()
                        Spacer(Modifier.height(16.dp))
                    }
                    productGrid(
                        products = state.products,
                        onProductClick = onProductClick,
                        showRating = true
                    )
                }
            }
        }
    }
}