package com.sajjadio.quickshop.presentation.screen.categories


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sajjadio.quickshop.domain.useCase.GetAllCategoriesUseCase
import com.sajjadio.quickshop.domain.utils.Resource
import com.sajjadio.quickshop.presentation.screen.common.CategoryUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
) : ViewModel() {

    private val _uiStats = MutableStateFlow(CategoryUiState())
    val uiStats = _uiStats.asStateFlow()

    init {
        loadCategoriesData()
    }

    private fun loadCategoriesData() {
        viewModelScope.launch {
            getAllCategoriesUseCase().collect { resource ->
                when (resource) {
                    Resource.Loading -> _uiStats.update { it.copy(isLoading = true) }
                    is Resource.Success -> {
                        _uiStats.update {
                            CategoryUiState(
                                categories = resource.data,
                                isLoading = false
                            )
                        }

                    }

                    is Resource.Error -> {
                        _uiStats.update { state ->
                            state.copy(
                                isLoading = false,
                                error = resource.errorMessage.toString()
                            )
                        }
                    }
                }
            }
        }
    }

}