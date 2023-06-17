package com.sajjadio.quickshop.presentation.screen.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.sajjadio.quickshop.presentation.screen.common.ProductUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchVewModel @Inject constructor() : ViewModel() {
    private val _state = mutableStateOf(ProductUiState())
    val state: State<ProductUiState> = _state
    val searchQuery = mutableStateOf("")

    fun setSearchQuery(query: String) {
        searchQuery.value = query
    }

    private fun loadProductData(): ProductUiState {
        return ProductUiState(

        )
    }
}