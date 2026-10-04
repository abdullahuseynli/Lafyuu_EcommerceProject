package com.example.lafyuu_projectfinal.screen.offer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lafyuu_projectfinal.common.Resource
import com.example.lafyuu_projectfinal.model.Product
import com.example.lafyuu_projectfinal.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class OfferUiState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val products: List<Product> = emptyList()
)

@HiltViewModel
class OfferViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _state = MutableStateFlow(OfferUiState())
    val state: StateFlow<OfferUiState> = _state

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            val results = listOf("mens-shoes", "womens-bags", "womens-shoes")
                .map { slug -> async { repository.getProductsByCategory(slug) } }
                .map { it.await() }

            val products = results
                .filterIsInstance<Resource.Success<List<Product>>>()
                .flatMap { it.data }
            val error = results.filterIsInstance<Resource.Error>().firstOrNull()?.message

            _state.update {
                it.copy(
                    isLoading = false,
                    // hamısı uğursuz olubsa xəta göstər, qismən uğurlu olsa məhsulları göstər
                    error = if (products.isEmpty()) error else null,
                    products = products
                )
            }
        }
    }
}