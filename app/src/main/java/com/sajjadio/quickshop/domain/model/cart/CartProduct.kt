package com.sajjadio.quickshop.domain.model.cart


data class CartProduct(
    val category: String = "",
    val id: Int = 0,
    val image: String = "",
    val price: Double = 0.0,
    val quantity: Int = 0,
    )