package com.sajjadio.quickshop.domain.repository

import com.sajjadio.quickshop.data.dataSource.remote.model.cart.CartDto
import com.sajjadio.quickshop.data.dataSource.remote.model.products.ProductDto
import com.sajjadio.quickshop.domain.model.cart.Cart
import com.sajjadio.quickshop.domain.model.products.Product
import com.sajjadio.quickshop.domain.model.user.User
import com.sajjadio.quickshop.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ShopRepository {

    fun getAllProducts(): Flow<Resource<List<Product>>>

    fun getProductById(productId: Int): Flow<Resource<Product>>

    fun sortAllProducts(sort: String): Flow<Resource<List<ProductDto>>>

    fun getAllCategories(): Flow<Resource<List<String>>>

    fun getAllProductsByCategory(category: String): Flow<Resource<List<Product>>>

   suspend fun getAllCartsByUserId(userId: Int): Flow<Resource<MutableList<Cart>>>

    fun getCartById(cartId: Int): Flow<Resource<CartDto>>

    fun sortAllCarts(sort: String): Flow<Resource<CartDto>>

    fun getUserById(userId: Int): Flow<Resource<List<User>>>

}