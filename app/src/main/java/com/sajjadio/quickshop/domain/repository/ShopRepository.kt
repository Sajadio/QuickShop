package com.sajjadio.quickshop.domain.repository

import com.sajjadio.quickshop.data.dataSource.remote.model.cart.CartDto
import com.sajjadio.quickshop.data.dataSource.remote.model.products.ProductDto
import com.sajjadio.quickshop.domain.model.cart.Cart
import com.sajjadio.quickshop.domain.model.products.Product
import com.sajjadio.quickshop.domain.model.user.User
import com.sajjadio.quickshop.domain.utils.Resource

interface ShopRepository {

    suspend fun getAllProducts(): Resource<List<Product>>

    suspend fun getProductById(productId: Int): Resource<Product>

    suspend fun sortAllProducts(sort: String): Resource<List<ProductDto>>

    suspend fun getAllCategories(): Resource<List<String>>

    suspend fun getAllProductsByCategory(category: String): Resource<List<Product>>

    suspend fun getAllCartsByUserId(userId: Int): Resource<MutableList<Cart>>

    suspend fun getCartById(cartId: Int): Resource<CartDto>

    suspend fun sortAllCarts(sort: String): Resource<CartDto>

    suspend fun getUserById(userId: Int): Resource<List<User>>

}