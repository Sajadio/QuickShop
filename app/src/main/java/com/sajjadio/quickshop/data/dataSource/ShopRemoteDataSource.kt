package com.sajjadio.quickshop.data.dataSource

import com.sajjadio.quickshop.data.remote.model.cart.Cart
import com.sajjadio.quickshop.data.remote.model.cart.Carts
import com.sajjadio.quickshop.data.remote.model.products.ProductDto
import retrofit2.Response

interface ShopRemoteDataSource {
    suspend fun getProducts(): Response<List<ProductDto>>

    fun getProductById(productId: Int): Response<ProductDto>

    fun sortProducts(sort: String): Response<List<ProductDto>>

    suspend fun getCategories(): Response<List<String>>

    fun getProductByCategory(category: String): Response<List<ProductDto>>

    fun getCarts(): Response<Carts>

    fun getCartById(cartId: Int): Response<Cart>

    fun sortCarts(sort: String): Response<Carts>
}