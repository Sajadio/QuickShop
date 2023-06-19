package com.sajjadio.quickshop.domain.model.products


data class Product(
    val category: String = "",
    val description: String = "",
    val id: Int = 0,
    val image: String = "",
    val price: Double = 0.0,
    val rating: Rating = Rating(),
    val title: String = ""
)