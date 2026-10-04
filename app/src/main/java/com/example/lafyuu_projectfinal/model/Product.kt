package com.example.lafyuu_projectfinal.model

data class Review(
    val rating: Int?,
    val comment: String?,
    val date: String?,
    val reviewerName: String?,
    val reviewerEmail: String?
)

data class Product(
    val id: Int,
    val title: String,
    val description: String? = null,
    val category: String? = null,
    val price: Double,
    val discountPercentage: Double = 0.0,
    val rating: Double = 0.0,
    val stock: Int? = null,
    val brand: String? = null,
    val warrantyInformation: String? = null,
    val shippingInformation: String? = null,
    val availabilityStatus: String? = null,
    val returnPolicy: String? = null,
    val reviews: List<Review>? = null,
    val thumbnail: String? = null,
    val images: List<String>? = null
) {
    val discountedPrice: Double
        get() = price * (1 - discountPercentage / 100)
}