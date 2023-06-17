package com.sajjadio.quickshop.data.remote

import com.sajjadio.quickshop.data.remote.model.cart.Cart
import com.sajjadio.quickshop.data.remote.model.cart.Carts
import com.sajjadio.quickshop.data.remote.model.categories.Categories
import com.sajjadio.quickshop.data.remote.model.products.ProductDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ShopApiService {

    @GET("products")
    suspend fun getProducts(): Response<List<ProductDto>>

    @GET("products/{id}")
    fun getProductById(@Path("id") productId: Int): Response<ProductDto>

    @GET("products")
    fun sortProducts(@Query("sort") sort: String): Response<List<ProductDto>>

    @GET("products/categories")
    fun getCategories(): Response<Categories>

    @GET("products/category/{category}")
    fun getProductByCategory(@Path("category") category: String): Response<List<ProductDto>>

    @GET("carts")
    fun getCarts(): Response<Carts>

    @GET("carts/{id}")
    fun getCartById(@Path("id") cartId: Int): Response<Cart>

    @GET("carts")
    fun sortCarts(@Query("sort") sort: String): Response<Carts>
}