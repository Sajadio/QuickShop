package com.sajjadio.quickshop.data.dataSource

import com.sajjadio.quickshop.data.dataSource.remote.model.cart.CartDto
import com.sajjadio.quickshop.data.dataSource.remote.model.products.ProductDto
import com.sajjadio.quickshop.data.dataSource.remote.model.user.UserDto
import retrofit2.Response

interface ShopRemoteDataSource {
    suspend fun getAllProducts(): Response<List<ProductDto>>

    suspend fun getProductById(productId: Int): Response<ProductDto>

    suspend fun sortAllProducts(sort: String): Response<List<ProductDto>>

    suspend fun getAllCategories(): Response<List<String>>

    suspend fun getAllProductsByCategory(category: String): Response<List<ProductDto>>

    suspend fun getAllCartsByUserId(userId: Int): Response<List<CartDto>>

    suspend fun getCartById(cartId: Int): Response<CartDto>

    suspend fun sortAllCarts(sort: String): Response<CartDto>
    suspend fun getUserById(userId: Int): Response<List<UserDto>>
}