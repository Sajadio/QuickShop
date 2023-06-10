package com.sajjadio.quickshop.presentation.screen.cart

import kotlin.random.Random

data class CartUiState(
    val carts: List<Cart> = listOf()
)

data class Cart(
    val id: Int = Random.nextInt(),
    val productImage: String = "",
    val order: Int = 0,
    val count: Int = 0,
    val productCategory: String = "",
    val productPrice: Double = 0.0,
    val date: String = "",
    val isShipping: Boolean = false,
    val productState: String = ""
)
