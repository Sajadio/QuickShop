package com.sajjadio.quickshop.presentation.screen.common


data class CategoryUiState(
    val categories: List<String>? = listOf(),
    val isLoading: Boolean = false,
    val error: String = ""
)