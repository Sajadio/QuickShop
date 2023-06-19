package com.sajjadio.quickshop.domain.useCase

import com.sajjadio.quickshop.domain.model.products.Product
import com.sajjadio.quickshop.domain.repository.ShopRepository
import com.sajjadio.quickshop.domain.utils.Resource
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductByQuery @Inject constructor(
    private val repository: ShopRepository
) {

    @OptIn(FlowPreview::class)
    operator fun invoke(query: String): Flow<Resource<List<Product>>> {
        return flow {
            repository
                .getAllProducts()
                .debounce(500L)
                .collect { resource ->
                    when (resource) {
                        Resource.Loading -> emit(Resource.Loading)
                        is Resource.Success -> {
                            emit(
                                Resource.Success(
                                    checkIfQueryEqualsProductName(
                                        query,
                                        resource.data
                                    )
                                )
                            )
                        }

                        is Resource.Error -> emit(Resource.Error(resource.errorMessage.toString()))
                    }
                }
        }
    }

    private fun checkIfQueryEqualsProductName(
        query: String,
        data: List<Product>?
    ): List<Product>? {
        return data?.filter { product ->
            product.title.contains(query, ignoreCase = true)
        }
    }

}