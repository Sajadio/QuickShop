package com.sajjadio.quickshop.domain.model.products


import com.google.gson.annotations.SerializedName

data class Rating(
    val count: Int = 0,
    val rate: Double = 0.0
)