package com.sajjadio.quickshop.presentation.screen.common

import kotlin.random.Random

data class ProductUiState(
    val products: List<Product> = listOf()
)

data class Product(
    val id: Int = Random.nextInt(),
    val poster: String = "",
    val title: String = "",
    val category: String = "",
    val rate: Double = 0.0,
    val price: Double = 0.0
)
