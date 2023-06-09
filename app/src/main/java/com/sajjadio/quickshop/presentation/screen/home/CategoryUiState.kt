package com.sajjadio.quickshop.presentation.screen.home

import androidx.annotation.DrawableRes
import kotlin.random.Random


data class CategoryUiState(
    val categories: List<Category> = listOf()
)

data class Category(
    val id: Int = Random.nextInt(),
    @DrawableRes val poster: Int = 0,
    val title: String = ""
)
