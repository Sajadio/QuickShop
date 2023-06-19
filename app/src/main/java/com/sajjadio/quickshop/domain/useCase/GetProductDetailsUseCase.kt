package com.sajjadio.quickshop.domain.useCase

import com.sajjadio.quickshop.domain.model.products.Product
import com.sajjadio.quickshop.domain.repository.ShopRepository
import com.sajjadio.quickshop.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductDetailsUseCase @Inject constructor(
    private val repository: ShopRepository
) {

    operator fun invoke(id: Int): Flow<Resource<Product>> {
        return repository.getProductById(id)
    }
}