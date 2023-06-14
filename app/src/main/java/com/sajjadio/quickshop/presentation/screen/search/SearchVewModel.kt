package com.sajjadio.quickshop.presentation.screen.search

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.sajjadio.quickshop.presentation.screen.common.Product
import com.sajjadio.quickshop.presentation.screen.common.ProductUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SearchVewModel @Inject constructor() : ViewModel() {
    private val _state = mutableStateOf(ProductUiState())
    val state: State<ProductUiState> = _state
    val searchQuery = mutableStateOf("")

    fun setSearchQuery(query: String) {
        searchQuery.value = query
        query.takeIf { it.isNotBlank() }?.let {
            loadProductData().products.filter { it.title.contains(query) }.let {
                _state.value.products = it
            }

        }
    }

    private fun loadProductData(): ProductUiState {
        return ProductUiState(
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
//                Product(
//                    poster = "https://www.extoggery.com/wp-content/uploads/2017/03/Mens-Clothing2.jpg",
//                    title = "Men cloths 2",
//                    category = "Men cloths 1",
//                    rate = 3.3,
//                    price = 99.9,
//                ),
//                Product(
//                    poster = "https://th.bing.com/th/id/OIP.DbaY3GMyL6VPOyI8fAQq5gHaLH?pid=ImgDet&rs=1",
//                    title = "Women cloths 2",
//                    category = "Women cloths 2",
//                    rate = 4.3,
//                    price = 88.9,
//                ),
//                Product(
//                    poster = "https://th.bing.com/th/id/R.070ff861251f51b3075897d4f458f1cb?rik=Lwz7zGpkDUfOgQ&pid=ImgRaw&r=0",
//                    title = "Iphone 14 pro max",
//                    category = "Electronic",
//                    rate = 4.3,
//                    price = 1500.4,
//                ),
//                Product(
//                    poster = "https://th.bing.com/th/id/OIP.fMYrj0OZRQnpif4dwciRnwHaGz?pid=ImgDet&rs=1",
//                    title = "Fashion world",
//                    category = "Jewelery",
//                    rate = 4.3,
//                    price = 999.9,
//                ),
//                Product(
//                    poster = "https://th.bing.com/th/id/OIP.vL1YYVdKnKnGvwz2WWYIcQHaF5?pid=ImgDet&w=199&h=158&c=7&dpr=1.3",
//                    title = "Mac book pro",
//                    category = "Electronic",
//                    rate = 4.3,
//                    price = 1500.4,
//                ),
            )
        )
    }
}