package com.example.lafyuu_projectfinal.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.lafyuu_projectfinal.R
import com.example.lafyuu_projectfinal.screen.components.PrimaryButton
import com.example.lafyuu_projectfinal.screen.components.StarRating
import com.example.lafyuu_projectfinal.screen.detail.components.ColorSelector
import com.example.lafyuu_projectfinal.screen.detail.components.ProductImagePager
import com.example.lafyuu_projectfinal.screen.detail.components.ReviewSection
import com.example.lafyuu_projectfinal.screen.detail.components.SizeSelector
import com.example.lafyuu_projectfinal.screen.detail.components.SpecificationSection
import com.example.lafyuu_projectfinal.screen.detail.components.productColors
import com.example.lafyuu_projectfinal.screen.detail.components.shoeSizes
import com.example.lafyuu_projectfinal.screen.home.components.ProductRowSection
import com.example.lafyuu_projectfinal.ui.theme.Blue63
import com.example.lafyuu_projectfinal.ui.theme.BlueFF
import com.example.lafyuu_projectfinal.ui.theme.GrayB1

@Composable
fun ProductDetailScreen(
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit,
    onProductClick: (Int) -> Unit,
    viewModel: ProductDetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val product = state.product

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(painterResource(R.drawable.ic_back), "Back", tint = GrayB1)
            }
            Text(
                text = product?.title ?: "",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Blue63,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = onSearchClick) {
                Icon(painterResource(R.drawable.ic_search), "Search", tint = GrayB1)
            }
            IconButton(onClick = { }) {
                Icon(painterResource(R.drawable.ic_more), "More", tint = GrayB1)
            }
        }

        Box(modifier = Modifier.weight(1f)) {
            when {
                state.isLoading ->
                    CircularProgressIndicator(Modifier.align(Alignment.Center))

                state.error != null || product == null -> Column(
                    Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Xəta: ${state.error ?: "Məhsul tapılmadı"}")
                    Spacer(Modifier.height(8.dp))
                    Button(onClick = { viewModel.load() }) { Text("Yenidən cəhd et") }
                }

                else -> Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    ProductImagePager(
                        images = product.images.orEmpty()
                            .ifEmpty { listOfNotNull(product.thumbnail) }
                    )

                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.Top
                        ) {
                            Text(
                                text = product.title,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Blue63,
                                lineHeight = 30.sp,
                                modifier = Modifier.weight(1f)
                            )
                            IconButton(onClick = { viewModel.toggleFavorite() }) {
                                if (state.isFavorite) {
                                    Icon(
                                        painterResource(R.drawable.ic_love_filled),
                                        contentDescription = "Remove from favorites",
                                        tint = Color.Unspecified
                                    )
                                } else {
                                    Icon(
                                        painterResource(R.drawable.ic_love),
                                        contentDescription = "Add to favorites",
                                        tint = GrayB1
                                    )
                                }
                            }
                        }

                        Spacer(Modifier.height(8.dp))
                        StarRating(rating = product.rating, starSize = 16.dp)

                        Spacer(Modifier.height(12.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "$%.2f".format(product.discountedPrice),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = BlueFF
                            )
                            if (product.discountPercentage > 0) {
                                Spacer(Modifier.width(12.dp))
                                Text(
                                    text = "$%.2f".format(product.price),
                                    fontSize = 12.sp,
                                    color = GrayB1,
                                    textDecoration = TextDecoration.LineThrough
                                )
                            }
                        }

                        if (product.category?.contains("shoes") == true) {
                            Spacer(Modifier.height(24.dp))
                            SizeSelector(
                                sizes = shoeSizes,
                                selected = state.selectedSize,
                                onSelect = viewModel::selectSize
                            )
                        }

                        Spacer(Modifier.height(24.dp))
                        ColorSelector(
                            colors = productColors,
                            selectedIndex = state.selectedColorIndex,
                            onSelect = viewModel::selectColor
                        )

                        Spacer(Modifier.height(24.dp))
                        SpecificationSection(product = product)

                        Spacer(Modifier.height(24.dp))
                        ReviewSection(
                            reviews = product.reviews.orEmpty(),
                            averageRating = product.rating
                        )

                        ProductRowSection(
                            title = "You Might Also Like",
                            products = state.related,
                            onSeeMoreClick = { },
                            onProductClick = onProductClick
                        )
                        Spacer(Modifier.height(8.dp))
                    }
                }
            }
        }
        if (product != null) {
            PrimaryButton(
                text = "Add To Cart",
                onClick = { },
                modifier = Modifier
                    .padding(16.dp)
                    .navigationBarsPadding()
            )
        }
    }
}