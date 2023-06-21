package com.sajjadio.quickshop.data.remote.model.user


import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("city")
    val city: String?,
    @SerializedName("geolocation")
    val geolocation: Geolocation?,
    @SerializedName("number")
    val number: Int?,
    @SerializedName("street")
    val street: String?,
    @SerializedName("zipcode")
    val zipcode: String?
)