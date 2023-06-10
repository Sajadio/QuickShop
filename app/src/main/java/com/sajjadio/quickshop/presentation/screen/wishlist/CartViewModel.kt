package com.sajjadio.quickshop.presentation.screen.wishlist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor() : ViewModel() {

    private val _state = mutableStateOf(CartUiState())
    val state: State<CartUiState> = _state

    init {
        loadCartData()
    }

    private fun loadCartData() {
        _state.value = CartUiState(
            carts = listOf(
                Cart(
                    productImage = "https://th.bing.com/th/id/OIP.oei55yyhppyd6XOvBBIm2wHaHa?pid=ImgDet&rs=1",
                    order = 6235,
                    count = 2,
                    productCategory = "Women cloths",
                    productPrice = 109.95,
                    date = "3/5/2023",
                ),
                Cart(
                    productImage = "https://th.bing.com/th/id/OIP.vL1YYVdKnKnGvwz2WWYIcQHaF5?pid=ImgDet&w=199&h=158&c=7&dpr=1.3",
                    order = 4235,
                    count = 1,
                    productCategory = "Electronic",
                    productPrice = 999.9,
                    date = "3/5/2023",
                    isShipping = true,
                ),
            )
        )
    }
}