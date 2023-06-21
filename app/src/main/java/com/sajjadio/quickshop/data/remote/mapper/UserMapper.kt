package com.sajjadio.quickshop.data.remote.mapper

import com.sajjadio.quickshop.data.remote.model.user.NameDto
import com.sajjadio.quickshop.data.remote.model.user.UserDto
import com.sajjadio.quickshop.domain.model.user.Name
import com.sajjadio.quickshop.domain.model.user.User


internal fun UserDto.mapToUser(): User {
    return User(
        email = email,
        id = id,
        name = name.mapToName(),
        username = username,
    )
}

private fun NameDto.mapToName(): Name {
    return Name(
        firstname = firstname,
        lastname = lastname,
    )
}