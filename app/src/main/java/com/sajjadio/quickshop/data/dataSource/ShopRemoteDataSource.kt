package com.sajjadio.quickshop.data.dataSource

import com.sajjadio.quickshop.data.dataSource.remote.model.cart.CartDto
import com.sajjadio.quickshop.data.dataSource.remote.model.products.ProductDto
import com.sajjadio.quickshop.data.dataSource.remote.model.user.UserDto

interface ShopRemoteDataSource {

    suspend fun getAllProducts(): List<ProductDto>

    suspend fun getProductById(productId: Int): ProductDto

    suspend fun sortAllProducts(sort: String): List<ProductDto>

    suspend fun getAllCategories(): List<String>

    suspend fun getAllProductsByCategory(category: String): List<ProductDto>

    suspend fun getAllCartsByUserId(userId: Int): List<CartDto>

    suspend fun getCartById(cartId: Int): CartDto

    suspend fun sortAllCarts(sort: String): CartDto
    suspend fun getUserById(userId: Int): List<UserDto>
}