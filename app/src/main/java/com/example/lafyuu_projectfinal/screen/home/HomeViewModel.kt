package com.example.lafyuu_projectfinal.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lafyuu_projectfinal.common.Resource
import com.example.lafyuu_projectfinal.model.Category
import com.example.lafyuu_projectfinal.model.Product
import com.example.lafyuu_projectfinal.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val categories: List<Category> = emptyList(),
    val flashSale: List<Product> = emptyList(),
    val megaSale: List<Product> = emptyList(),
    val recommended: List<Product> = emptyList()
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state: StateFlow<HomeUiState> = _state

    init {
        loadHome()
    }

    fun loadHome() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            val categoriesDef = async { repository.getCategories() }
            val flashDef = async { repository.getProductsByCategory("mens-shoes") }
            val megaDef = async { repository.getProductsByCategory("womens-bags") }
            val recommendedDef = async { repository.getProducts(limit = 10) }

            val categories = categoriesDef.await()
            val flash = flashDef.await()
            val mega = megaDef.await()
            val recommended = recommendedDef.await()

            val errorMessage = listOf(categories, flash, mega, recommended)
                .filterIsInstance<Resource.Error>()
                .firstOrNull()?.message

            _state.update {
                it.copy(
                    isLoading = false,
                    error = errorMessage,
                    categories = (categories as? Resource.Success)?.data ?: emptyList(),
                    flashSale = (flash as? Resource.Success)?.data ?: emptyList(),
                    megaSale = (mega as? Resource.Success)?.data ?: emptyList(),
                    recommended = (recommended as? Resource.Success)?.data ?: emptyList()
                )
            }
        }
    }
}