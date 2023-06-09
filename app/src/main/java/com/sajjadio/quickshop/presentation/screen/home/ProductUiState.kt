package com.sajjadio.quickshop.presentation.screen.home

import kotlin.random.Random


data class ProductUiState(
    val products: List<Products> = listOf()
)

data class Products(
    val id: Int = Random.nextInt(),
    val poster: String = "",
    val title: String = "",
    val category: String = "",
    val rate: Double = 0.0,
    val price: Double = 0.0
)
