package com.example.lafyuu_projectfinal.screen.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lafyuu_projectfinal.model.Review
import com.example.lafyuu_projectfinal.screen.components.SectionHeader
import com.example.lafyuu_projectfinal.screen.components.StarRating
import com.example.lafyuu_projectfinal.ui.theme.Blue63
import com.example.lafyuu_projectfinal.ui.theme.BlueFF
import com.example.lafyuu_projectfinal.ui.theme.GrayB1

@Composable
fun ReviewSection(
    reviews: List<Review>,
    averageRating: Double,
    modifier: Modifier = Modifier
) {
    if (reviews.isEmpty()) return

    var expanded by remember { mutableStateOf(false) }
    val visible = if (expanded) reviews else reviews.take(1)

    Column(modifier = modifier.fillMaxWidth()) {
        if (reviews.size > 1) {
            SectionHeader(
                title = "Review Product",
                actionText = if (expanded) "See Less" else "See More",
                onActionClick = { expanded = !expanded }
            )
        } else {
            Text("Review Product", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Blue63)
        }

        Spacer(Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            StarRating(rating = averageRating, starSize = 16.dp)
            Spacer(Modifier.width(8.dp))
            Text(
                text = "%.1f (%d Review)".format(averageRating, reviews.size),
                fontSize = 10.sp,
                color = GrayB1
            )
        }

        Spacer(Modifier.height(16.dp))

        visible.forEach { review ->
            ReviewItem(review)
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
private fun ReviewItem(review: Review) {
    val name = review.reviewerName?.takeIf { it.isNotBlank() } ?: "Anonymous"

    Row(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(BlueFF.copy(alpha = 0.15f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = name.first().uppercase(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = BlueFF
            )
        }

        Spacer(Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(name, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Blue63)
            Spacer(Modifier.height(4.dp))
            StarRating(rating = (review.rating ?: 0).toDouble(), starSize = 12.dp)

            review.comment?.let {
                Spacer(Modifier.height(8.dp))
                Text(it, fontSize = 12.sp, color = GrayB1, lineHeight = 20.sp)
            }

            review.date?.take(10)?.let {
                Spacer(Modifier.height(8.dp))
                Text(it, fontSize = 10.sp, color = GrayB1)
            }
        }
    }
}