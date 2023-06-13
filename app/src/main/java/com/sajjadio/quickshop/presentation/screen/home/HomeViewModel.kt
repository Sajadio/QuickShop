package com.sajjadio.quickshop.presentation.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.sajjadio.quickshop.R
import com.sajjadio.quickshop.presentation.screen.common.Category
import com.sajjadio.quickshop.presentation.screen.common.CategoryUiState
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
        _productUiState.value = ProductUiState(
            products = listOf(
                Product(
                    poster = "https://th.bing.com/th/id/OIP.oei55yyhppyd6XOvBBIm2wHaHa?pid=ImgDet&rs=1",
                    title = "Women cloths 1",
                    category = "Women cloths 1",
                    rate = 2.3,
                    price = 109.0,
                ),
                Product(
                    poster = "https://th.bing.com/th/id/R.9dd3470fbcb28febaebbf2b93385a9f6?rik=UoufCYGzZ%2f5XRQ&pid=ImgRaw&r=0",
                    title = "Men cloths 1",
                    category = "Men cloths 1",
                    rate = 3.3,
                    price = 99.9,
                ),
                Product(
                    poster = "https://www.extoggery.com/wp-content/uploads/2017/03/Mens-Clothing2.jpg",
                    title = "Men cloths 2",
                    category = "Men cloths 1",
                    rate = 3.3,
                    price = 99.9,
                ),
                Product(
                    poster = "https://th.bing.com/th/id/OIP.DbaY3GMyL6VPOyI8fAQq5gHaLH?pid=ImgDet&rs=1",
                    title = "Women cloths 2",
                    category = "Women cloths 2",
                    rate = 4.3,
                    price = 88.9,
                ),
                Product(
                    poster = "https://th.bing.com/th/id/R.070ff861251f51b3075897d4f458f1cb?rik=Lwz7zGpkDUfOgQ&pid=ImgRaw&r=0",
                    title = "Iphone 14 pro max",
                    category = "Electronic",
                    rate = 4.3,
                    price = 1500.4,
                ),
                Product(
                    poster = "https://th.bing.com/th/id/OIP.fMYrj0OZRQnpif4dwciRnwHaGz?pid=ImgDet&rs=1",
                    title = "Fashion world",
                    category = "Jewelery",
                    rate = 4.3,
                    price = 999.9,
                ),
                Product(
                    poster = "https://th.bing.com/th/id/OIP.vL1YYVdKnKnGvwz2WWYIcQHaF5?pid=ImgDet&w=199&h=158&c=7&dpr=1.3",
                    title = "Mac book pro",
                    category = "Electronic",
                    rate = 4.3,
                    price = 1500.4,
                ),
            )
        )
    }
}