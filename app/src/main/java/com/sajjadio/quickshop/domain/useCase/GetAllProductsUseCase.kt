package com.sajjadio.quickshop.domain.useCase

import com.sajjadio.quickshop.domain.model.products.Product
import com.sajjadio.quickshop.domain.repository.ShopRepository
import com.sajjadio.quickshop.domain.utils.Resource
import javax.inject.Inject

class GetAllProductsUseCase @Inject constructor(
    private val repository: ShopRepository
) {

    suspend operator fun invoke(): Resource<List<Product>> {
        return  repository.getAllProducts()
    }
}

