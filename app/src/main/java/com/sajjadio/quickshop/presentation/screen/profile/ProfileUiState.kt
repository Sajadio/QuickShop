package com.sajjadio.quickshop.presentation.screen.profile

import com.sajjadio.quickshop.domain.model.user.User


data class ProfileUiState(
    val user: User? = User(),
    val isLoading: Boolean = false,
    val error: String = ""
)