package com.sajjadio.quickshop.data.remote.model.user


import com.google.gson.annotations.SerializedName

data class Geolocation(
    @SerializedName("lat")
    val lat: String?,
    @SerializedName("long")
    val long: String?
)