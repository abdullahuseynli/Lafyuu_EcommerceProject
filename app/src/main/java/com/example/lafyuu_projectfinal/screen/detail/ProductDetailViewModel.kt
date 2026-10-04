package com.example.lafyuu_projectfinal.screen.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lafyuu_projectfinal.common.Resource
import com.example.lafyuu_projectfinal.model.Product
import com.example.lafyuu_projectfinal.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProductDetailUiState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val product: Product? = null,
    val isFavorite: Boolean = false,
    val selectedSize: String = "7",
    val selectedColorIndex: Int = 0,
    val related: List<Product> = emptyList()
)

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val repository: ProductRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val productId: Int = checkNotNull(savedStateHandle["productId"])

    private val _state = MutableStateFlow(ProductDetailUiState())
    val state: StateFlow<ProductDetailUiState> = _state

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            when (val result = repository.getProduct(productId)) {
                is Resource.Success -> {
                    _state.update { it.copy(isLoading = false, product = result.data) }
                    result.data.category?.let { loadRelated(it) }
                }

                is Resource.Error ->
                    _state.update { it.copy(isLoading = false, error = result.message) }

                Resource.Loading -> Unit
            }
        }
    }

    private suspend fun loadRelated(category: String) {
        val result = repository.getProductsByCategory(category)
        if (result is Resource.Success) {
            _state.update { s ->
                s.copy(related = result.data.filter { it.id != productId })
            }
        }
    }

    fun toggleFavorite() = _state.update { it.copy(isFavorite = !it.isFavorite) }

    fun selectSize(size: String) = _state.update { it.copy(selectedSize = size) }

    fun selectColor(index: Int) = _state.update { it.copy(selectedColorIndex = index) }
}