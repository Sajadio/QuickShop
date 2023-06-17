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
    override suspend fun getProducts(): Response<List<ProductDto>>{
        return api.getProducts()
    }

    override fun getProductById(productId: Int): Response<ProductDto> {
        return api.getProductById(productId)
    }

    override fun sortProducts(sort: String): Response<List<ProductDto>> {
        return api.sortProducts(sort)
    }

    override suspend fun getCategories(): Response<List<String>>{
        return api.getCategories()
    }

    override fun getProductByCategory(category: String): Response<List<ProductDto>> {
        return api.getProductByCategory(category)
    }

    override fun getCarts(): Response<Carts> {
        return api.getCarts()
    }

    override fun getCartById(cartId: Int): Response<Cart> {
        return api.getCartById(cartId)
    }

    override fun sortCarts(sort: String): Response<Carts> {
        return api.sortCarts(sort)
    }
}