package com.sajjadio.quickshop.presentation.screen.cart

import com.sajjadio.quickshop.domain.model.cart.Cart

data class CartsUiState(
    val carts: List<Cart>? = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)