package com.sajjadio.quickshop.data.model.cart


import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("productId")
    val productId: Int?,
    @SerializedName("quantity")
    val quantity: Int?
)