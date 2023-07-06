package com.sajjadio.quickshop.presentation.screen.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sajjadio.quickshop.domain.useCase.GetProductByQueryUseCase
import com.sajjadio.quickshop.domain.utils.Resource
import com.sajjadio.quickshop.presentation.screen.common.ProductUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchVewModel @Inject constructor(
    private val getProductByQueryUseCase: GetProductByQueryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState = _uiState.asStateFlow()
    val searchQuery = mutableStateOf("")

    fun setSearchQuery(query: String) {
        searchQuery.value = query
        getProductsByName(query)
    }

    private fun getProductsByName(query: String) {
        viewModelScope.launch {
            getProductByQueryUseCase(query)
                .collect { resource ->
                    when (resource) {
                        Resource.Loading -> _uiState.update { it.copy(isLoading = true) }
                        is Resource.Success -> {
                            _uiState.update {
                                it.copy(
                                    products = resource.data,
                                    isLoading = false,
                                )
                            }
                        }

                        is Resource.Error -> {
                            _uiState.update {
                                it.copy(
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