package com.sajjadio.quickshop.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sajjadio.quickshop.domain.useCase.GetAllAdsUseCase
import com.sajjadio.quickshop.domain.useCase.GetAllCategoriesUseCase
import com.sajjadio.quickshop.domain.useCase.GetAllProductsUseCase
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
    private val getAllAdsUseCase: GetAllAdsUseCase,
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
    private val getAllProductsUseCase: GetAllProductsUseCase,
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

    fun addToCart(id: Int) {
        // TODO
    }

    private fun loadAdsData() {
        viewModelScope.launch {
            getAllAdsUseCase().collect { resource ->
                when (resource) {
                    Resource.Loading -> _adUiState.update { it.copy(isLoading = true) }
                    is Resource.Success -> {
                        _adUiState.update {
                            AdUIState(
                                ads = resource.data,
                                isLoading = false
                            )
                        }
                    }

                    is Resource.Error -> {
                        _adUiState.update { state ->
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

    private fun loadCategoriesData() {
        viewModelScope.launch {
            getAllCategoriesUseCase().collect { resource ->
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

                    is Resource.Error -> {
                        _categoryUiState.update { state ->
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

    private fun loadProductsData() {
        viewModelScope.launch {
            getAllProductsUseCase().collect { resource ->
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

                    is Resource.Error -> {
                        _productUiState.update { state ->
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
}