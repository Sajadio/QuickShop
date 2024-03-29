package com.sajjadio.quickshop.domain.useCase

import com.sajjadio.quickshop.domain.model.cart.Cart
import com.sajjadio.quickshop.domain.repository.ShopRepository
import com.sajjadio.quickshop.domain.utils.Resource
import javax.inject.Inject

class GetAllCartsByUserIdUseCase @Inject constructor(
    private val repository: ShopRepository
) {

    suspend operator fun invoke(userId:Int): Resource<MutableList<Cart>> {
        return repository.getAllCartsByUserId(userId)
    }

}