package com.sajjadio.quickshop.presentation.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.sajjadio.quickshop.R
import com.sajjadio.quickshop.presentation.screen.common.Category
import com.sajjadio.quickshop.presentation.screen.common.CategoryUiState
import com.sajjadio.quickshop.presentation.screen.common.ProductUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _adsUiState = mutableStateOf(AdsUIState())
    val adsUiState: State<AdsUIState> = _adsUiState

    private val _categoryUiState = mutableStateOf(CategoryUiState())
    val categoryUiState: State<CategoryUiState> = _categoryUiState

    private val _productUiState = mutableStateOf(ProductUiState())
    val productUiState: State<ProductUiState> = _productUiState

    init {
        loadAdsData()
        loadCategoryData()
        loadProductData()
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

    private fun loadCategoryData() {
        _categoryUiState.value = CategoryUiState(
            categories = listOf(
                Category(
                    poster = R.drawable.women_cloths,
                    title = "Women Cloths"
                ),
                Category(
                    poster = R.drawable.electroinc,
                    title = "Electronic"
                ),
                Category(
                    poster = R.drawable.jewelery,
                    title = "Jewelery"
                ),
                Category(
                    poster = R.drawable.men_clothes,
                    title = "Men Cloths"
                )
            )
        )
    }

    private fun loadProductData() {

    }
}