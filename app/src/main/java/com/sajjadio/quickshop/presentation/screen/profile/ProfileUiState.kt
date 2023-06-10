package com.sajjadio.quickshop.presentation.screen.profile

data class ProfileUiState(
    val userName: String = "",
    val userImage: String = "",
    val information: CartInformation = CartInformation()
)

data class CartInformation(
        val orderNumber: Int = 0,
        val shippingAddressNumber: Int = 0,
        val reviews: Int = 0
)
