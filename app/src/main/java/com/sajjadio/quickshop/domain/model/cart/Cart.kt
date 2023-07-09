package com.sajjadio.quickshop.domain.model.cart

import kotlin.random.Random

data class Cart(
    val date: String = "",
    val id: Int = 0,
    val cartProduct: CartProduct,
    val userId: Int = 0,
    val isShipping: Boolean = Random(10).nextBoolean(),
    val productState: String =  if (isShipping) "Shipped" else "Processing"
)