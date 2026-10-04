package com.example.lafyuu_projectfinal.repository

import com.example.lafyuu_projectfinal.api.ApiService
import com.example.lafyuu_projectfinal.common.Resource
import com.example.lafyuu_projectfinal.model.Category
import com.example.lafyuu_projectfinal.model.Product
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val api: ApiService
) {

    suspend fun getProducts(limit: Int = 30, skip: Int = 0): Resource<List<Product>> =
        safeCall { api.getProducts(limit, skip).products }

    suspend fun getCategories(): Resource<List<Category>> =
        safeCall { api.getCategories() }

    suspend fun getProductsByCategory(slug: String): Resource<List<Product>> =
        safeCall { api.getProductsByCategory(slug).products }

    suspend fun getProduct(id: Int): Resource<Product> =
        safeCall { api.getProduct(id) }

    suspend fun searchProducts(query: String): Resource<List<Product>> =
        safeCall { api.searchProducts(query).products }

    private suspend fun <T> safeCall(block: suspend () -> T): Resource<T> =
        try {
            Resource.Success(block())
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "Something went wrong")
        }
}