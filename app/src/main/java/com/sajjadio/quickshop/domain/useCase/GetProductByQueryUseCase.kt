package com.sajjadio.quickshop.domain.useCase

import com.sajjadio.quickshop.domain.model.products.Product
import com.sajjadio.quickshop.domain.repository.ShopRepository
import com.sajjadio.quickshop.domain.utils.Resource
import javax.inject.Inject

class GetProductByQueryUseCase @Inject constructor(
    private val repository: ShopRepository
) {

    suspend operator fun invoke(query: String): Resource<List<Product>> {
        return when (val result = repository.getAllProducts()) {
            is Resource.Success -> {
                Resource.Success(checkIfQueryEqualsProductName(query, result.data))
            }
            is Resource.Error -> {
                Resource.Error(result.errorMessage)
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