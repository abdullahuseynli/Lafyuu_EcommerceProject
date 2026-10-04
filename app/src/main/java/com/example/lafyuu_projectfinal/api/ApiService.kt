package com.example.lafyuu_projectfinal.api

import com.example.lafyuu_projectfinal.model.Category
import com.example.lafyuu_projectfinal.model.Product
import com.example.lafyuu_projectfinal.model.ProductsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("products")
    suspend fun getProducts(
        @Query("limit") limit: Int = 30,
        @Query("skip") skip: Int = 0
    ): ProductsResponse

    @GET("products/categories")
    suspend fun getCategories(): List<Category>

    @GET("products/category/{slug}")
    suspend fun getProductsByCategory(
        @Path("slug") slug: String
    ): ProductsResponse

    @GET("products/{id}")
    suspend fun getProduct(@Path("id") id: Int): Product

    @GET("products/search")
    suspend fun searchProducts(@Query("q") query: String): ProductsResponse
}