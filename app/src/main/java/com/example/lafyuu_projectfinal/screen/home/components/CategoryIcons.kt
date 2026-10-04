package com.example.lafyuu_projectfinal.screen.home.components

import androidx.annotation.DrawableRes
import com.example.lafyuu_projectfinal.R


@DrawableRes
fun categoryIconRes(slug: String): Int? = when (slug) {
    "mens-shirts" -> R.drawable.ic_cat_man_shirt
    "womens-dresses" -> R.drawable.ic_cat_dress
    "mens-watches" -> R.drawable.ic_cat_man_work_equipment
    "womens-bags" -> R.drawable.ic_cat_woman_bag
    "mens-shoes" -> R.drawable.ic_cat_man_shoes
    else -> null
}