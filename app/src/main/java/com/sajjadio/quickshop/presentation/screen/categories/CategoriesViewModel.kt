package com.sajjadio.quickshop.presentation.screen.categories

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.sajjadio.quickshop.R
import com.sajjadio.quickshop.presentation.screen.common.Category
import com.sajjadio.quickshop.presentation.screen.common.CategoryUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor() : ViewModel() {

    private val _stats = mutableStateOf(CategoryUiState())
    val stats: State<CategoryUiState> = _stats

    init {
        loadCategoryData()
    }

    private fun loadCategoryData() {
        _stats.value = CategoryUiState(
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

}