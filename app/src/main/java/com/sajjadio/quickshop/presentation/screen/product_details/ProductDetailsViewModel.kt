package com.sajjadio.quickshop.presentation.screen.product_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(ProductDetailsUiState())
    val state: State<ProductDetailsUiState> = _state

    val itemCount = mutableStateOf(0)

    private val args: ProductDetailsArgs = ProductDetailsArgs(savedStateHandle = savedStateHandle)

    init {
        loadProductDetailsData()
    }

    private fun loadProductDetailsData() {
        _state.value = ProductDetailsUiState(
            id = 1,
            poster = "https://th.bing.com/th/id/OIP.oei55yyhppyd6XOvBBIm2wHaHa?pid=ImgDet&rs=1",
            title = "Women cloths 1",
            category = "Women cloths 1",
            rate = 2.3,
            price = 109.0,
        )
    }
}