package com.sajjadio.quickshop.data.repository

import com.sajjadio.quickshop.data.model.cart.Cart
import com.sajjadio.quickshop.data.model.cart.Carts
import com.sajjadio.quickshop.data.model.categories.Categories
import com.sajjadio.quickshop.data.model.products.Product
import com.sajjadio.quickshop.data.model.products.Products
import com.sajjadio.quickshop.data.remote.ShopApiService
import com.sajjadio.quickshop.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val shopApi: ShopApiService
) : Repository {
    override fun getProducts(): Flow<Products> {
        TODO("Not yet implemented")
    }

    override fun getProduct(productId: Int): Flow<Product> {
        TODO("Not yet implemented")
    }

    override fun sortProducts(sort: String): Flow<Products> {
        TODO("Not yet implemented")
    }

    override fun getCategories(): Flow<Categories> {
        TODO("Not yet implemented")
    }

    override fun getProductByCategory(category: String): Flow<Products> {
        TODO("Not yet implemented")
    }

    override fun getCarts(): Flow<Carts> {
        TODO("Not yet implemented")
    }

    override fun getCartById(cartId: Int): Flow<Cart> {
        TODO("Not yet implemented")
    }

    override fun sortCarts(sort: String): Flow<Carts> {
        TODO("Not yet implemented")
    }
}