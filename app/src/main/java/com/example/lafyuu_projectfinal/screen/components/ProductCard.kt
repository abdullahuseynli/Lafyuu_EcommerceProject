package com.example.lafyuu_projectfinal.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.lafyuu_projectfinal.model.Product
import com.example.lafyuu_projectfinal.ui.theme.BlueFF
import com.example.lafyuu_projectfinal.ui.theme.GrayB1
import com.example.lafyuu_projectfinal.ui.theme.GreyFF
import com.example.lafyuu_projectfinal.ui.theme.RedFB
import kotlin.math.roundToInt

@Composable
fun ProductCard(
    product: Product,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    width: Dp? = 141.dp,
    showRating: Boolean = false
) {
    Column(
        modifier = modifier
            .then(if (width != null) Modifier.width(width) else Modifier)
            .clip(RoundedCornerShape(5.dp))
            .border(1.dp, GreyFF, RoundedCornerShape(5.dp))
            .background(Color.White)
            .clickable { onClick() }
            .padding(12.dp)
    ) {
        AsyncImage(
            model = product.thumbnail,
            contentDescription = product.title,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .height(109.dp)
                .clip(RoundedCornerShape(5.dp))
        )

        Spacer(Modifier.height(8.dp))


        Text(
            text = product.title,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = BlueFF,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            lineHeight = 15.sp,
            modifier = Modifier.height(30.dp)
        )

        if (showRating) {
            Spacer(Modifier.height(6.dp))
            StarRating(rating = product.rating)
        }

        Spacer(Modifier.height(8.dp))

        Text(
            text = "$%.2f".format(product.discountedPrice),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = BlueFF
        )

        Spacer(Modifier.height(4.dp))

        if (product.discountPercentage > 0) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "$%.2f".format(product.price),
                    fontSize = 10.sp,
                    color = GrayB1,
                    textDecoration = TextDecoration.LineThrough
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "${product.discountPercentage.roundToInt()}% Off",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = RedFB
                )
            }
        } else {
            Spacer(Modifier.height(15.dp))
        }
    }
}