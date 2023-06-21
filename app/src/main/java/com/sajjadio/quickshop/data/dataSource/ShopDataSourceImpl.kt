package com.sajjadio.quickshop.data.dataSource

import com.sajjadio.quickshop.data.remote.model.cart.Cart
import com.sajjadio.quickshop.data.remote.model.cart.Carts
import com.sajjadio.quickshop.data.remote.model.products.ProductDto
import com.sajjadio.quickshop.data.remote.ShopApiService
import com.sajjadio.quickshop.data.remote.model.user.UserDto
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

    override suspend fun getAllCarts(): Response<Carts> {
        return api.getAllCarts()
    }

    override suspend fun getCartById(cartId: Int): Response<Cart> {
        return api.getCartById(cartId)
    }

    override suspend fun sortAllCarts(sort: String): Response<Carts> {
        return api.sortAllCarts(sort)
    }

    override suspend fun getUser(userId: Int): Response<List<UserDto>> {
        return api.getUser(userId)
    }
}