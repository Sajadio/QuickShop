package com.sajjadio.quickshop.data.repository

import com.sajjadio.quickshop.data.dataSource.ShopRemoteDataSource
import com.sajjadio.quickshop.data.model.cart.Cart
import com.sajjadio.quickshop.data.model.cart.Carts
import com.sajjadio.quickshop.data.model.categories.Categories
import com.sajjadio.quickshop.data.model.products.Product
import com.sajjadio.quickshop.data.model.products.Products
import com.sajjadio.quickshop.domain.repository.ShopRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShopShopRepositoryImpl @Inject constructor(
    private val shopRemoteDataSource: ShopRemoteDataSource
) : ShopRepository {
    override fun getProducts(): Flow<Products> {
        return shopRemoteDataSource.getProducts()
    }

    override fun getProduct(productId: Int): Flow<Product> {
        return shopRemoteDataSource.getProductById(productId)
    }

    override fun sortProducts(sort: String): Flow<Products> {
        return shopRemoteDataSource.sortProducts(sort)
    }

    override fun getCategories(): Flow<Categories> {
        return shopRemoteDataSource.getCategories()
    }

    override fun getProductByCategory(category: String): Flow<Products> {
        return shopRemoteDataSource.getProductByCategory(category)
    }

    override fun getCarts(): Flow<Carts> {
        return shopRemoteDataSource.getCarts()
    }

    override fun getCartById(cartId: Int): Flow<Cart> {
        return shopRemoteDataSource.getCartById(cartId)
    }

    override fun sortCarts(sort: String): Flow<Carts> {
        return shopRemoteDataSource.sortCarts(sort)
    }
}