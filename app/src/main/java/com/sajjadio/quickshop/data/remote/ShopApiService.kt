package com.sajjadio.quickshop.data.remote

import com.sajjadio.quickshop.data.remote.model.cart.Cart
import com.sajjadio.quickshop.data.remote.model.cart.Carts
import com.sajjadio.quickshop.data.remote.model.products.ProductDto
import com.sajjadio.quickshop.data.remote.model.user.UserDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ShopApiService {

    @GET("products")
    suspend fun getAllProducts(): Response<List<ProductDto>>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") productId: Int): Response<ProductDto>

    @GET("products")
    suspend fun sortAllProducts(@Query("sort") sort: String): Response<List<ProductDto>>

    @GET("products/categories")
    suspend fun getAllCategories(): Response<List<String>>

    @GET("products/category/{category}")
    suspend fun getAllProductsByCategory(@Path("category") category: String): Response<List<ProductDto>>

    @GET("carts")
    fun getAllCarts(): Response<Carts>

    @GET("carts/{id}")
    suspend fun getCartById(@Path("id") cartId: Int): Response<Cart>

    @GET("carts")
    suspend fun sortAllCarts(@Query("sort") sort: String): Response<Carts>

    @GET("users/")
    suspend fun getUser(@Query("id") userId: Int): Response<List<UserDto>>
}