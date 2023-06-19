package com.sajjadio.quickshop.presentation.screen.product_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sajjadio.quickshop.domain.useCase.GetProductDetailsUseCase
import com.sajjadio.quickshop.domain.utils.Resource
import com.sajjadio.quickshop.presentation.screen.common.ProductUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val getProductDetailsUseCase: GetProductDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductDetailsUiState())
    val uiState = _uiState.asStateFlow()

    val itemCount = mutableStateOf(0)

    private val args: ProductDetailsArgs = ProductDetailsArgs(savedStateHandle = savedStateHandle)

    init {
        loadProductDetailsData(args.id)
    }

    private fun loadProductDetailsData(id: Int) {
        viewModelScope.launch {
            getProductDetailsUseCase(id).collect { resource ->
                when (resource) {
                    Resource.Loading -> _uiState.update { it.copy(isLoading = true) }
                    is Resource.Success -> {
                        _uiState.update { state ->
                            state.copy(
                                product = resource.data,
                                isLoading = false
                            )
                        }
                    }

                    is Resource.Error -> _uiState.update { state ->
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