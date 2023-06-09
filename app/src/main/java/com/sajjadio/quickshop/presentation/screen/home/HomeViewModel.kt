package com.sajjadio.quickshop.presentation.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private var _adsUiState = mutableStateOf(AdsUIState())
    val adsUiState: State<AdsUIState> = _adsUiState

    init {
        loadAdsData()
    }

    private fun loadAdsData() {
        _adsUiState.value = AdsUIState(
            ads = listOf(
                Ads(poster = "https://th.bing.com/th/id/OIP.Bx1l8fWdGahgkQDGiYPpJAHaE8?pid=ImgDet&rs=1"),
                Ads(poster = "https://i2-prod.mirror.co.uk/incoming/article8687186.ece/ALTERNATES/s615/Main-VIP-air-stewardess-reveals-exactly-what-goes-on-aboard-private-jets.jpg"),
                Ads(poster = "https://th.bing.com/th/id/OIP.N1m4xKxK4RnwQbtKSm5TUAHaE8?pid=ImgDet&w=600&h=400&rs=1"),
                Ads(poster = "https://th.bing.com/th/id/OIP.uAoQWoZVeYBiU0Tjj4BhkAHaE8?pid=ImgDet&w=500&h=334&rs=1"),
            )
        )
    }
}