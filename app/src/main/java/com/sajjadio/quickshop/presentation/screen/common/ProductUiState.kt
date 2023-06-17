package com.sajjadio.quickshop.presentation.screen.common

import com.sajjadio.quickshop.domain.model.products.Product

data class ProductUiState(
    val products: List<Product>? = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)
