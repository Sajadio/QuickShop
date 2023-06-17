package com.sajjadio.quickshop.data.dataSource

import com.sajjadio.quickshop.data.model.cart.Cart
import com.sajjadio.quickshop.data.model.cart.Carts
import com.sajjadio.quickshop.data.model.categories.Categories
import com.sajjadio.quickshop.data.model.products.Product
import com.sajjadio.quickshop.data.model.products.Products
import com.sajjadio.quickshop.data.remote.ShopApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShopDataSourceImpl @Inject constructor(
    private val api: ShopApiService
) : ShopRemoteDataSource {
    override fun getProducts(): Flow<Products> {
       return api.getProducts()
    }

    override fun getProductById(productId: Int): Flow<Product> {
        return api.getProductById(productId)
    }

    override fun sortProducts(sort: String): Flow<Products> {
        return api.sortProducts(sort)
    }

    override fun getCategories(): Flow<Categories> {
        return api.getCategories()
    }

    override fun getProductByCategory(category: String): Flow<Products> {
        return api.getProductByCategory(category)
    }

    override fun getCarts(): Flow<Carts> {
        return api.getCarts()
    }

    override fun getCartById(cartId: Int): Flow<Cart> {
        return api.getCartById(cartId)
    }

    override fun sortCarts(sort: String): Flow<Carts> {
        return api.sortCarts(sort)
    }
}