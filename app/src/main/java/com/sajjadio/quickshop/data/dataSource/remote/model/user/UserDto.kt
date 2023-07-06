package com.sajjadio.quickshop.data.dataSource.remote.model.user


import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("address")
    val addressDto: AddressDto,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: NameDto,
    @SerializedName("password")
    val password: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("__v")
    val v: Int
)