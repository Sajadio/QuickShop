package com.sajjadio.quickshop.presentation.screen.product_details

import kotlin.random.Random

data class ProductDetailsUiState(
    val id:Int = 0,
    val poster: String = "",
    val title: String = "",
    val category: String = "",
    val rate: Double = 0.0,
    val price: Double = 0.0
)
