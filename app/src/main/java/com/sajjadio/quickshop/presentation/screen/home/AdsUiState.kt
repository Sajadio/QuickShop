package com.sajjadio.quickshop.presentation.screen.home

import kotlin.random.Random

data class AdsUIState(
    val ads: List<Ads> = listOf()
)

data class Ads(
    val id: Int = Random.nextInt(),
    val poster: String = ""
)
