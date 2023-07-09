package com.sajjadio.quickshop.domain.useCase

import com.sajjadio.quickshop.domain.utils.Resource
import com.sajjadio.quickshop.presentation.screen.home.Ad
import kotlinx.coroutines.delay
import javax.inject.Inject

class GetAllAdsUseCase @Inject constructor() {
    suspend operator fun invoke(): Resource<List<Ad>> {
        return try {
            delay(500L)
            Resource.Success(setAds())
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

    private fun setAds(): List<Ad> {
        return listOf(
            Ad(image = "https://th.bing.com/th/id/OIP.Bx1l8fWdGahgkQDGiYPpJAHaE8?pid=ImgDet&rs=1"),
            Ad(image = "https://i2-prod.mirror.co.uk/incoming/article8687186.ece/ALTERNATES/s615/Main-VIP-air-stewardess-reveals-exactly-what-goes-on-aboard-private-jets.jpg"),
            Ad(image = "https://th.bing.com/th/id/OIP.N1m4xKxK4RnwQbtKSm5TUAHaE8?pid=ImgDet&w=600&h=400&rs=1"),
            Ad(image = "https://th.bing.com/th/id/OIP.uAoQWoZVeYBiU0Tjj4BhkAHaE8?pid=ImgDet&w=500&h=334&rs=1"),
        )
    }
}