package com.sajjadio.quickshop.data.remote.model.products


import com.google.gson.annotations.SerializedName

data class RatingDto(
    @SerializedName("count")
    val count: Int,
    @SerializedName("rate")
    val rate: Double
)