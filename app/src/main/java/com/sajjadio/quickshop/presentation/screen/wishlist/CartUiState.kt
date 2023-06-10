package com.sajjadio.quickshop.presentation.screen.wishlist

import kotlin.random.Random

data class CartUiState(
    val id: Int = Random.nextInt(),
    val productImage: String = "",
    val order: Int = 0,
    val count: Int = 0,
    val productCategory: String = "",
    val productPrice: Double = 0.0,
    val date: String = "",
    val isShipping: Boolean = false
)
