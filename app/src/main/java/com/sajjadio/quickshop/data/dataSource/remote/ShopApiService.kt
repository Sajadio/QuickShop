package com.sajjadio.quickshop.data.dataSource.remote

import com.sajjadio.quickshop.data.dataSource.remote.model.cart.CartDto
import com.sajjadio.quickshop.data.dataSource.remote.model.products.ProductDto
import com.sajjadio.quickshop.data.dataSource.remote.model.user.UserDto
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

    @GET("carts/user/{id}")
    suspend fun getAllCartsByUserId(@Path("id") userId:Int): Response<List<CartDto>>

    @GET("carts/{id}")
    suspend fun getCartById(@Path("id") cartId: Int): Response<CartDto>

    @GET("carts")
    suspend fun sortAllCarts(@Query("sort") sort: String): Response<CartDto>

    @GET("users/")
    suspend fun getUserById(@Query("id") userId: Int): Response<List<UserDto>>
}