package com.sajjadio.quickshop.domain.useCase

import com.sajjadio.quickshop.domain.model.user.User
import com.sajjadio.quickshop.domain.repository.ShopRepository
import com.sajjadio.quickshop.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserInformationUseCase @Inject constructor(
    private val repository: ShopRepository
) {

    operator fun invoke(userId: Int): Flow<Resource<List<User>>> {
        return repository.getUser(userId)
    }
}