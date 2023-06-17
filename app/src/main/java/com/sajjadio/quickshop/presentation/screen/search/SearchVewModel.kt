package com.sajjadio.quickshop.presentation.screen.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sajjadio.quickshop.domain.model.products.Product
import com.sajjadio.quickshop.domain.repository.ShopRepository
import com.sajjadio.quickshop.domain.utils.Resource
import com.sajjadio.quickshop.presentation.screen.common.ProductUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchVewModel @Inject constructor(
    private val repository: ShopRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ProductUiState())
    val state = _state.asStateFlow()
    val searchQuery = mutableStateOf("")

    fun setSearchQuery(query: String) {
        searchQuery.value = query
        getProductsByName(query)
    }

    @OptIn(FlowPreview::class)
    private fun getProductsByName(query: String) {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            repository
                .getProducts()
                .debounce(500L)
                .collect { resource ->
                    when (resource) {
                        Resource.Loading -> _state.update { it.copy(isLoading = true) }
                        is Resource.Success -> {
                            checkIfQueryEquuleusProductTitle(query, resource.data)
                        }

                        is Resource.Error -> _state.update {
                            it.copy(
                                isLoading = false,
                                error = resource.errorMessage.toString()
                            )
                        }
                    }
                }
        }

    }

    private fun checkIfQueryEquuleusProductTitle(query: String, data: List<Product>?) {
        data?.filter { product ->
            product.title.contains(query, ignoreCase = true)
        }?.let { products ->
            _state.update { state ->
                state.copy(
                    products = products,
                    isLoading = false
                )
            }
        }
    }
}