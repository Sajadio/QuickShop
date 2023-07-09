package com.sajjadio.quickshop.domain.useCase

import com.sajjadio.quickshop.domain.model.user.User
import com.sajjadio.quickshop.domain.repository.ShopRepository
import com.sajjadio.quickshop.domain.utils.Resource
import javax.inject.Inject

class GetUserInformationUseCase @Inject constructor(
    private val repository: ShopRepository
) {

    suspend operator fun invoke(userId: Int): Resource<List<User>> {
        return repository.getUserById(userId)
    }
}