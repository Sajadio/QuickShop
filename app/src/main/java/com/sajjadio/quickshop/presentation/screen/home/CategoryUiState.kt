package com.sajjadio.quickshop.presentation.screen.home

import kotlin.random.Random


data class CategoryUiState(
    val categories: List<Category> = listOf()
)

data class Category(
    val id: Int = Random.nextInt(),
    val poster: String = "",
    val title: String = ""
)
