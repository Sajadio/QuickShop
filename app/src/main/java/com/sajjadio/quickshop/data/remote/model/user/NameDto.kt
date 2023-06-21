package com.sajjadio.quickshop.data.remote.model.user


import com.google.gson.annotations.SerializedName

data class NameDto(
    @SerializedName("firstname")
    val firstname: String,
    @SerializedName("lastname")
    val lastname: String
)