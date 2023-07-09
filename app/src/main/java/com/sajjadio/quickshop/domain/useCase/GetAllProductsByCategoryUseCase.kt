package com.sajjadio.quickshop.domain.useCase

import com.sajjadio.quickshop.domain.model.products.Product
import com.sajjadio.quickshop.domain.repository.ShopRepository
import com.sajjadio.quickshop.domain.utils.Resource
import javax.inject.Inject

class GetAllProductsByCategoryUseCase @Inject constructor(
    private val repository: ShopRepository
) {
    suspend operator fun invoke(category: String): Resource<List<Product>> {
        return repository.getAllProductsByCategory(category)
    }
}