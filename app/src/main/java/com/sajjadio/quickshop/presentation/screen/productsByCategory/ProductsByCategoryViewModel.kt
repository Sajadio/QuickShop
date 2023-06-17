package com.sajjadio.quickshop.presentation.screen.productsByCategory

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.sajjadio.quickshop.presentation.screen.common.ProductUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductsByCategoryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(ProductUiState())
    val state: State<ProductUiState> = _state

     val args: ProductsByCategory = ProductsByCategory(savedStateHandle = savedStateHandle)

    init {
        loadProductData()
    }

    private fun loadProductData() {

    }
}