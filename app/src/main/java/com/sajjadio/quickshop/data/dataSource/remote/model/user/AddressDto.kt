package com.sajjadio.quickshop.data.dataSource.remote.model.user


import com.google.gson.annotations.SerializedName

data class AddressDto(
    @SerializedName("city")
    val city: String?,
    @SerializedName("geolocation")
    val geolocationDto: GeolocationDto?,
    @SerializedName("number")
    val number: Int?,
    @SerializedName("street")
    val street: String?,
    @SerializedName("zipcode")
    val zipcode: String?
)