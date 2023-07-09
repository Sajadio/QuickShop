package com.sajjadio.quickshop.domain.useCase

import com.sajjadio.quickshop.domain.repository.ShopRepository
import com.sajjadio.quickshop.domain.utils.Resource
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor(
    private val repository: ShopRepository
) {

    suspend operator fun invoke(): Resource<List<String>> {
        return  repository.getAllCategories()
    }
}