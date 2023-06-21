package com.sajjadio.quickshop.domain.model.user


data class User(
    val email: String = "",
    val id: Int = 0,
    val name: Name = Name(),
    val username: String = "",
)

data class Name(
    val firstname: String = "",
    val lastname: String = ""
)