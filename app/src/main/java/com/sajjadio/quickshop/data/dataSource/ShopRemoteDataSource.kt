package com.sajjadio.quickshop.data.dataSource

import com.sajjadio.quickshop.data.remote.model.cart.Cart
import com.sajjadio.quickshop.data.remote.model.cart.Carts
import com.sajjadio.quickshop.data.remote.model.products.ProductDto
import com.sajjadio.quickshop.data.remote.model.user.UserDto
import retrofit2.Response

interface ShopRemoteDataSource {
    suspend fun getAllProducts(): Response<List<ProductDto>>

    suspend fun getProductById(productId: Int): Response<ProductDto>

    suspend fun sortAllProducts(sort: String): Response<List<ProductDto>>

    suspend fun getAllCategories(): Response<List<String>>

    suspend fun getAllProductsByCategory(category: String): Response<List<ProductDto>>

    suspend fun getAllCarts(): Response<Carts>

    suspend fun getCartById(cartId: Int): Response<Cart>

    suspend fun sortAllCarts(sort: String): Response<Carts>
    suspend fun getUser(userId: Int): Response<List<UserDto>>
}