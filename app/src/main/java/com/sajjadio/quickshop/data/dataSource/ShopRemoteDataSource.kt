package com.sajjadio.quickshop.data.dataSource

import com.sajjadio.quickshop.data.model.cart.Cart
import com.sajjadio.quickshop.data.model.cart.Carts
import com.sajjadio.quickshop.data.model.categories.Categories
import com.sajjadio.quickshop.data.model.products.Product
import com.sajjadio.quickshop.data.model.products.Products
import kotlinx.coroutines.flow.Flow

interface ShopRemoteDataSource {
    fun getProducts(): Flow<Products>

    fun getProductById(productId: Int): Flow<Product>

    fun sortProducts(sort: String): Flow<Products>

    fun getCategories(): Flow<Categories>

    fun getProductByCategory(category: String): Flow<Products>

    fun getCarts(): Flow<Carts>

    fun getCartById(cartId: Int): Flow<Cart>

    fun sortCarts(sort: String): Flow<Carts>
}