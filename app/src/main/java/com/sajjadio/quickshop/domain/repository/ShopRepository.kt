package com.sajjadio.quickshop.domain.repository

import com.sajjadio.quickshop.data.remote.model.cart.Cart
import com.sajjadio.quickshop.data.remote.model.cart.Carts
import com.sajjadio.quickshop.data.remote.model.categories.Categories
import com.sajjadio.quickshop.data.remote.model.products.ProductDto
import com.sajjadio.quickshop.domain.model.products.Product
import com.sajjadio.quickshop.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ShopRepository {

    fun getProducts(): Flow<Resource<List<Product>>>

    fun getProduct(productId: Int): Flow<Resource<ProductDto>>

    fun sortProducts(sort: String): Flow<Resource<List<ProductDto>>>

    fun getCategories(): Flow<Resource<Categories>>

    fun getProductByCategory(category: String): Flow<Resource<List<ProductDto>>>

    fun getCarts(): Flow<Resource<Carts>>

    fun getCartById(cartId: Int): Flow<Resource<Cart>>

    fun sortCarts(sort: String): Flow<Resource<Carts>>

}