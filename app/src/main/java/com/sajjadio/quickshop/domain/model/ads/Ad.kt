package com.sajjadio.quickshop.domain.model.ads

import kotlin.random.Random

data class Ad(
    val id: Int = Random.nextInt(),
    val image: String = ""
)