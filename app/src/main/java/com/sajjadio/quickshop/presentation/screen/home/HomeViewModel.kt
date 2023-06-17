package com.sajjadio.quickshop.presentation.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sajjadio.quickshop.domain.repository.ShopRepository
import com.sajjadio.quickshop.domain.utils.Resource
import com.sajjadio.quickshop.presentation.screen.common.CategoryUiState
import com.sajjadio.quickshop.presentation.screen.common.ProductUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ShopRepository
) : ViewModel() {

    private val _adUiState = MutableStateFlow(AdUIState())
    val adUiState = _adUiState.asStateFlow()

    private val _categoryUiState = MutableStateFlow(CategoryUiState())
    val categoryUiState = _categoryUiState.asStateFlow()

    private val _productUiState = MutableStateFlow(ProductUiState())
    val productUiState = _productUiState.asStateFlow()

    init {
        loadAdsData()
        loadCategoriesData()
        loadProductsData()
    }

    private fun loadAdsData() {
        _adUiState.update {
            it.copy(
                ads = listOf(
                    Ad(image = "https://th.bing.com/th/id/OIP.Bx1l8fWdGahgkQDGiYPpJAHaE8?pid=ImgDet&rs=1"),
                    Ad(image = "https://i2-prod.mirror.co.uk/incoming/article8687186.ece/ALTERNATES/s615/Main-VIP-air-stewardess-reveals-exactly-what-goes-on-aboard-private-jets.jpg"),
                    Ad(image = "https://th.bing.com/th/id/OIP.N1m4xKxK4RnwQbtKSm5TUAHaE8?pid=ImgDet&w=600&h=400&rs=1"),
                    Ad(image = "https://th.bing.com/th/id/OIP.uAoQWoZVeYBiU0Tjj4BhkAHaE8?pid=ImgDet&w=500&h=334&rs=1"),
                ),
            )
        }
    }

    private fun loadCategoriesData() {
        viewModelScope.launch {
            repository.getCategories().collect { resource ->
                when (resource) {
                    Resource.Loading -> _categoryUiState.update { it.copy(isLoading = true) }
                    is Resource.Success -> {
                        _categoryUiState.update {
                            CategoryUiState(
                                categories = resource.data,
                                isLoading = false
                            )
                        }

                    }

                    is Resource.Error -> _categoryUiState.update { state ->
                        state.copy(
                            isLoading = false,
                            error = resource.errorMessage.toString()
                        )
                    }
                }
            }
        }
    }

    private fun loadProductsData() {
        viewModelScope.launch {
            repository.getProducts().collect { resource ->
                when (resource) {
                    Resource.Loading -> _productUiState.update { it.copy(isLoading = true) }
                    is Resource.Success -> {
                        _productUiState.update { state ->
                            state.copy(
                                products = resource.data,
                                isLoading = false
                            )
                        }
                    }

                    is Resource.Error -> _productUiState.update { state ->
                        state.copy(
                            isLoading = false,
                            error = resource.errorMessage.toString()
                        )
                    }
                }
            }
        }
    }
}