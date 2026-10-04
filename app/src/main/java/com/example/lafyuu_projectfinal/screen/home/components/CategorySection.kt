package com.example.lafyuu_projectfinal.screen.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lafyuu_projectfinal.model.Category
import com.example.lafyuu_projectfinal.screen.components.SectionHeader
import com.example.lafyuu_projectfinal.ui.theme.GrayB1
import com.example.lafyuu_projectfinal.ui.theme.GreyFF

@Composable
fun CategorySection(
    categories: List<Category>,
    onCategoryClick: (String) -> Unit,
    onMoreClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val visible = categories.filter { categoryIconRes(it.slug) != null }

    Column(modifier = modifier.fillMaxWidth()) {
        SectionHeader(
            title = "Category",
            actionText = "More Category",
            onActionClick = onMoreClick
        )
        Spacer(Modifier.height(12.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(visible, key = { it.slug }) { category ->
                CategoryItem(category = category, onClick = { onCategoryClick(category.slug) })
            }
        }
    }
}

@Composable
private fun CategoryItem(category: Category, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .width(70.dp)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .border(1.dp, GreyFF, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            val icon = categoryIconRes(category.slug)
            if (icon != null) {
                Image(
                    painter = painterResource(icon),
                    contentDescription = category.name,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
        Spacer(Modifier.height(8.dp))
        Text(
            text = category.name,
            fontSize = 10.sp,
            color = GrayB1,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}