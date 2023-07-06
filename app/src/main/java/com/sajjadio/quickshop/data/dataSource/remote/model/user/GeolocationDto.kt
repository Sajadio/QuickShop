package com.sajjadio.quickshop.data.dataSource.remote.model.user


import com.google.gson.annotations.SerializedName

data class GeolocationDto(
    @SerializedName("lat")
    val lat: String?,
    @SerializedName("long")
    val long: String?
)