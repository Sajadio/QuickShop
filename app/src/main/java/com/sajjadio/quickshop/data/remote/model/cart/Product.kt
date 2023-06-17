package com.sajjadio.quickshop.data.remote.model.cart


import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("productId")
    val productId: Int?,
    @SerializedName("quantity")
    val quantity: Int?
)