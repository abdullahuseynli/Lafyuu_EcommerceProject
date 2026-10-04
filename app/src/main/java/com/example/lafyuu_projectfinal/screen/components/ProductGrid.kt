package com.example.lafyuu_projectfinal.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lafyuu_projectfinal.model.Product

fun LazyListScope.productGrid(
    products: List<Product>,
    onProductClick: (Int) -> Unit,
    showRating: Boolean = true
) {
    items(products.chunked(2)) { rowProducts ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            rowProducts.forEach { product ->
                ProductCard(
                    product = product,
                    onClick = { onProductClick(product.id) },
                    modifier = Modifier.weight(1f),
                    width = null,
                    showRating = showRating
                )
            }
            if (rowProducts.size == 1) Spacer(Modifier.weight(1f))
        }
    }
}