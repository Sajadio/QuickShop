package com.sajjadio.quickshop.data.model.products


import com.google.gson.annotations.SerializedName

data class Rating(
    @SerializedName("count")
    val count: Int? = null,
    @SerializedName("rate")
    val rate: Double? = null
)