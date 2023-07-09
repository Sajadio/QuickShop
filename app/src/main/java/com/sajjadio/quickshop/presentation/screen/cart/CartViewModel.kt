package com.sajjadio.quickshop.presentation.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sajjadio.quickshop.domain.useCase.GetAllCartsByUserIdUseCase
import com.sajjadio.quickshop.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getAllCartsByUserIdUseCase: GetAllCartsByUserIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CartsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadProductsOfCartData()
    }

    private fun loadProductsOfCartData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            when (val resource = getAllCartsByUserIdUseCase(USER_ID)) {
                is Resource.Success -> {
                    _uiState.update { state ->
                        state.copy(
                            carts = resource.data,
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

    private companion object {
        const val USER_ID = 3
    }
}