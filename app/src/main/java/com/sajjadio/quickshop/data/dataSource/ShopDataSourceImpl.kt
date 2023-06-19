package com.sajjadio.quickshop.data.dataSource

import com.sajjadio.quickshop.data.remote.model.cart.Cart
import com.sajjadio.quickshop.data.remote.model.cart.Carts
import com.sajjadio.quickshop.data.remote.model.products.ProductDto
import com.sajjadio.quickshop.data.remote.ShopApiService
import retrofit2.Response
import javax.inject.Inject

class ShopDataSourceImpl @Inject constructor(
    private val api: ShopApiService
) : ShopRemoteDataSource {
    override suspend fun getAllProducts(): Response<List<ProductDto>>{
        return api.getAllProducts()
    }

    override fun getProductById(productId: Int): Response<ProductDto> {
        return api.getProductById(productId)
    }

    override fun sortAllProducts(sort: String): Response<List<ProductDto>> {
        return api.sortAllProducts(sort)
    }

    override suspend fun getAllCategories(): Response<List<String>>{
        return api.getAllCategories()
    }

    override fun getProductByCategory(category: String): Response<List<ProductDto>> {
        return api.getProductByCategory(category)
    }

    override fun getAllCarts(): Response<Carts> {
        return api.getAllCarts()
    }

    override fun getCartById(cartId: Int): Response<Cart> {
        return api.getCartById(cartId)
    }

    override fun sortAllCarts(sort: String): Response<Carts> {
        return api.sortAllCarts(sort)
    }
}