package com.sajjadio.quickshop.data.dataSource

import com.sajjadio.quickshop.data.remote.model.cart.Cart
import com.sajjadio.quickshop.data.remote.model.cart.Carts
import com.sajjadio.quickshop.data.remote.model.products.ProductDto
import retrofit2.Response

interface ShopRemoteDataSource {
    suspend fun getAllProducts(): Response<List<ProductDto>>

    fun getProductById(productId: Int): Response<ProductDto>

    fun sortAllProducts(sort: String): Response<List<ProductDto>>

    suspend fun getAllCategories(): Response<List<String>>

    fun getProductByCategory(category: String): Response<List<ProductDto>>

    fun getAllCarts(): Response<Carts>

    fun getCartById(cartId: Int): Response<Cart>

    fun sortAllCarts(sort: String): Response<Carts>
}