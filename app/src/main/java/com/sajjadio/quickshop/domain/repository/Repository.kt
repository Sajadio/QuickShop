package com.sajjadio.quickshop.domain.repository

import com.sajjadio.quickshop.data.model.cart.Cart
import com.sajjadio.quickshop.data.model.cart.Carts
import com.sajjadio.quickshop.data.model.categories.Categories
import com.sajjadio.quickshop.data.model.products.Products
import com.sajjadio.quickshop.data.model.products.Product
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getProducts(): Flow<Products>

    fun getProduct(productId: Int): Flow<Product>

    fun sortProducts(sort: String): Flow<Products>

    fun getCategories(): Flow<Categories>

    fun getProductByCategory(category: String): Flow<Products>

    fun getCarts(): Flow<Carts>

    fun getCartById(cartId: Int): Flow<Cart>

    fun sortCarts(sort: String): Flow<Carts>

}