package com.sajjadio.quickshop.data.remote

import com.sajjadio.quickshop.data.model.cart.Cart
import com.sajjadio.quickshop.data.model.cart.Carts
import com.sajjadio.quickshop.data.model.categories.Categories
import com.sajjadio.quickshop.data.model.products.Product
import com.sajjadio.quickshop.data.model.products.Products
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ShopApiService {

    @GET("products")
    fun getProducts(): Flow<Products>

    @GET("products/{id}")
    fun getProductById(@Path("id") productId: Int): Flow<Product>

    @GET("products")
    fun sortProducts(@Query("sort") sort: String): Flow<Products>

    @GET("products/categories")
    fun getCategories(): Flow<Categories>

    @GET("products/category/{category}")
    fun getProductByCategory(@Path("category") category: String): Flow<Products>

    @GET("carts")
    fun getCarts(): Flow<Carts>

    @GET("carts/{id}")
    fun getCartById(@Path("id") cartId: Int): Flow<Cart>

    @GET("carts")
    fun sortCarts(@Query("sort") sort: String): Flow<Carts>
}