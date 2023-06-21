package com.sajjadio.quickshop.domain.repository

import com.sajjadio.quickshop.data.remote.model.cart.Cart
import com.sajjadio.quickshop.data.remote.model.cart.Carts
import com.sajjadio.quickshop.data.remote.model.products.ProductDto
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

    fun getAllCarts(): Flow<Resource<Carts>>

    fun getCartById(cartId: Int): Flow<Resource<Cart>>

    fun sortAllCarts(sort: String): Flow<Resource<Carts>>

    fun getUser(userId: Int): Flow<Resource<List<User>>>

}