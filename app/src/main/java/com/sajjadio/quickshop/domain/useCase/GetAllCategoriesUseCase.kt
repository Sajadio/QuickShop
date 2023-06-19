package com.sajjadio.quickshop.domain.useCase

import com.sajjadio.quickshop.domain.repository.ShopRepository
import com.sajjadio.quickshop.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor(
    private val repository: ShopRepository
) {

    operator fun invoke(): Flow<Resource<List<String>>> {
        return repository.getAllCategories()
    }
}