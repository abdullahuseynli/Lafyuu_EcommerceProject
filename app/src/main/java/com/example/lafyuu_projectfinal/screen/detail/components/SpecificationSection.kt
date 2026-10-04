package com.example.lafyuu_projectfinal.screen.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lafyuu_projectfinal.model.Product
import com.example.lafyuu_projectfinal.ui.theme.Blue63
import com.example.lafyuu_projectfinal.ui.theme.GrayB1

@Composable
fun SpecificationSection(
    product: Product,
    modifier: Modifier = Modifier
) {
    val rows = listOf(
        "Brand" to product.brand,
        "Category" to product.category,
        "Warranty" to product.warrantyInformation,
        "Shipping" to product.shippingInformation,
        "Availability" to product.availabilityStatus,
        "Return" to product.returnPolicy
    ).mapNotNull { (label, value) -> value?.let { label to it } }

    Column(modifier = modifier.fillMaxWidth()) {
        Text("Specification", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Blue63)
        Spacer(Modifier.height(12.dp))

        rows.forEach { (label, value) ->
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "$label:",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Blue63,
                    modifier = Modifier.width(96.dp)
                )
                Text(
                    text = value,
                    fontSize = 12.sp,
                    color = GrayB1,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(Modifier.height(8.dp))
        }

        product.description?.let {
            Spacer(Modifier.height(4.dp))
            Text(it, fontSize = 12.sp, color = GrayB1, lineHeight = 20.sp)
        }
    }
}