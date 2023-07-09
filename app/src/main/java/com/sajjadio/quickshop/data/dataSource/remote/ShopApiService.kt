package com.sajjadio.quickshop.data.dataSource.remote

import com.sajjadio.quickshop.data.dataSource.ShopRemoteDataSource
import com.sajjadio.quickshop.data.dataSource.remote.model.cart.CartDto
import com.sajjadio.quickshop.data.dataSource.remote.model.products.ProductDto
import com.sajjadio.quickshop.data.dataSource.remote.model.user.UserDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ShopApiService : ShopRemoteDataSource {

    @GET("products")
    override suspend fun getAllProducts(): List<ProductDto>


    @GET("products/{id}")
    override suspend fun getProductById(@Path("id") productId: Int): ProductDto

    @GET("products")
    override suspend fun sortAllProducts(@Query("sort") sort: String): List<ProductDto>

    @GET("products/categories")
    override suspend fun getAllCategories(): List<String>

    @GET("products/category/{category}")
    override suspend fun getAllProductsByCategory(@Path("category") category: String): List<ProductDto>

    @GET("carts/user/{id}")
    override suspend fun getAllCartsByUserId(@Path("id") userId: Int): List<CartDto>

    @GET("carts/{id}")
    override suspend fun getCartById(@Path("id") cartId: Int): CartDto

    @GET("carts")
    override suspend fun sortAllCarts(@Query("sort") sort: String): CartDto

    @GET("users/")
    override suspend fun getUserById(@Query("id") userId: Int): List<UserDto>
}