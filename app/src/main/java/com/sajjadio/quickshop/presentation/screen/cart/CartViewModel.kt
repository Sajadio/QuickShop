package com.sajjadio.quickshop.presentation.screen.cart

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
            count = 6,
            shippingFee = 60.00,
            total = 2248.04,
            carts = listOf(
                Cart(
                    productImage = "https://th.bing.com/th/id/OIP.oei55yyhppyd6XOvBBIm2wHaHa?pid=ImgDet&rs=1",
                    order = 6235,
                    count = 2,
                    productCategory = "women cloths",
                    productPrice = 109.95,
                    date = "3/5/2023",
                    productState = "Processing"
                ),
                Cart(
                    productImage = "https://th.bing.com/th/id/OIP.vL1YYVdKnKnGvwz2WWYIcQHaF5?pid=ImgDet&w=199&h=158&c=7&dpr=1.3",
                    order = 4235,
                    count = 1,
                    productCategory = "electronic",
                    productPrice = 999.9,
                    date = "3/5/2023",
                    isShipping = true,
                    productState = "Shipped"
                ),
                Cart(
                    productImage = "https://th.bing.com/th/id/R.070ff861251f51b3075897d4f458f1cb?rik=Lwz7zGpkDUfOgQ&pid=ImgRaw&r=0",
                    order = 4435,
                    count = 5,
                    productCategory = "electronic",
                    productPrice = 1200.9,
                    date = "3/5/2023",
                    productState = "Shipped"
                ),
                Cart(
                    productImage = "https://www.extoggery.com/wp-content/uploads/2017/03/Mens-Clothing2.jpg",
                    order = 4435,
                    count = 3,
                    productCategory = "men cloths",
                    productPrice = 1200.9,
                    date = "3/5/2023",
                    productState = "Shipped"
                ),
                Cart(
                    productImage = "https://th.bing.com/th/id/OIP.fMYrj0OZRQnpif4dwciRnwHaGz?pid=ImgDet&rs=1",
                    order = 4435,
                    count = 3,
                    productCategory = "jewelery",
                    productPrice = 1200.9,
                    date = "3/5/2023",
                    productState = "Shipped"
                ),
            )
        )
    }
}