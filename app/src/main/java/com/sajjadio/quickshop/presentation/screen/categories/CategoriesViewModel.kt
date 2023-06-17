package com.sajjadio.quickshop.presentation.screen.categories

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.sajjadio.quickshop.R
import com.sajjadio.quickshop.presentation.screen.common.CategoryUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor() : ViewModel() {

    private val _stats = mutableStateOf(CategoryUiState())
    val stats: State<CategoryUiState> = _stats

}