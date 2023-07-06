package com.sajjadio.quickshop.data.dataSource.remote.model.cart


import com.google.gson.annotations.SerializedName

data class CartDto(
    @SerializedName("date")
    val date: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("products")
    val products: List<CartProductDto>,
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("__v")
    val v: Int
)