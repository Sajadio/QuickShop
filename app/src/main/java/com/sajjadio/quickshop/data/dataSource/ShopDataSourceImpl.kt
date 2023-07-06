package com.sajjadio.quickshop.data.dataSource

import com.sajjadio.quickshop.data.dataSource.remote.model.cart.CartDto
import com.sajjadio.quickshop.data.dataSource.remote.model.products.ProductDto
import com.sajjadio.quickshop.data.dataSource.remote.ShopApiService
import com.sajjadio.quickshop.data.dataSource.remote.model.user.UserDto
import retrofit2.Response
import javax.inject.Inject

class ShopDataSourceImpl @Inject constructor(
    private val api: ShopApiService
) : ShopRemoteDataSource {
    override suspend fun getAllProducts(): Response<List<ProductDto>> {
        return api.getAllProducts()
    }

    override suspend fun getProductById(productId: Int): Response<ProductDto> {
        return api.getProductById(productId)
    }

    override suspend fun sortAllProducts(sort: String): Response<List<ProductDto>> {
        return api.sortAllProducts(sort)
    }

    override suspend fun getAllCategories(): Response<List<String>> {
        return api.getAllCategories()
    }

    override suspend fun getAllProductsByCategory(category: String): Response<List<ProductDto>> {
        return api.getAllProductsByCategory(category)
    }

    override suspend fun getAllCartsByUserId(userId: Int): Response<List<CartDto>> {
        return api.getAllCartsByUserId(userId)
    }

    override suspend fun getCartById(cartId: Int): Response<CartDto> {
        return api.getCartById(cartId)
    }

    override suspend fun sortAllCarts(sort: String): Response<CartDto> {
        return api.sortAllCarts(sort)
    }

    override suspend fun getUserById(userId: Int): Response<List<UserDto>> {
        return api.getUserById(userId)
    }
}