package com.sajjadio.quickshop.domain.useCase

import com.sajjadio.quickshop.domain.model.products.Product
import com.sajjadio.quickshop.domain.repository.ShopRepository
import com.sajjadio.quickshop.domain.utils.Resource
import javax.inject.Inject

class GetProductDetailsUseCase @Inject constructor(
    private val repository: ShopRepository
) {

    suspend operator fun invoke(id: Int): Resource<Product> {
        return  repository.getProductById(id)
    }
}