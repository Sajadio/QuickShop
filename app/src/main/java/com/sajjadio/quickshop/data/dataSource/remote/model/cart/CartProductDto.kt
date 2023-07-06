package com.sajjadio.quickshop.data.dataSource.remote.model.cart


import com.google.gson.annotations.SerializedName

data class CartProductDto(
    @SerializedName("productId")
    val productId: Int,
    @SerializedName("quantity")
    val quantity: Int
)