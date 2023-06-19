package com.sajjadio.quickshop.presentation.screen.product_details

import com.sajjadio.quickshop.domain.model.products.Product

data class ProductDetailsUiState(
    val product: Product? = Product(),
    val isLoading: Boolean = false,
    val error: String = ""
)
