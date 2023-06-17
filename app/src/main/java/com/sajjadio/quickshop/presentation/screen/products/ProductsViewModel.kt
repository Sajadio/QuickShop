package com.sajjadio.quickshop.presentation.screen.products

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sajjadio.quickshop.domain.repository.ShopRepository
import com.sajjadio.quickshop.domain.utils.Resource
import com.sajjadio.quickshop.presentation.screen.common.ProductUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val repository: ShopRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ProductUiState())
    val state = _state.asStateFlow()

    init {
        loadProductData()
    }

    private fun loadProductData() {
        viewModelScope.launch {
            repository.getProducts().collect { resource ->
                when (resource) {
                    Resource.Loading -> _state.update { it.copy(isLoading = true) }
                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                products = resource.data,
                                isLoading = false
                            )
                        }
                    }

                    is Resource.Error -> _state.update {
                        it.copy(
                            isLoading = false,
                            error = resource.errorMessage.toString()
                        )
                    }
                }
            }
        }
    }
}