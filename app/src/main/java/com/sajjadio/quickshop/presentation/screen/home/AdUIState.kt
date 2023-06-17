package com.sajjadio.quickshop.presentation.screen.home

import kotlin.random.Random

data class AdUIState(
    val ads: List<Ad> = listOf(),
    val isLoading: Boolean = false,
    val error: String = ""
)

data class Ad(
    val id: Int = Random.nextInt(),
    val image: String = ""
)