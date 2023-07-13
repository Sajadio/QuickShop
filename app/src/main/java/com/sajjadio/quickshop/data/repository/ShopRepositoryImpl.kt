package com.sajjadio.quickshop.data.repository

import com.sajjadio.quickshop.data.dataSource.ShopRemoteDataSource
import com.sajjadio.quickshop.data.dataSource.remote.mapper.mapToProduct
import com.sajjadio.quickshop.data.dataSource.remote.mapper.mapToUser
import com.sajjadio.quickshop.data.dataSource.remote.model.cart.CartDto
import com.sajjadio.quickshop.data.dataSource.remote.model.products.ProductDto
import com.sajjadio.quickshop.domain.model.cart.Cart
import com.sajjadio.quickshop.domain.model.cart.CartProduct
import com.sajjadio.quickshop.domain.model.products.Product
import com.sajjadio.quickshop.domain.model.user.User
import com.sajjadio.quickshop.domain.repository.ShopRepository
import com.sajjadio.quickshop.domain.utils.Resource
import javax.inject.Inject

class ShopRepositoryImpl @Inject constructor(
    private val shopRemoteDataSource: ShopRemoteDataSource,
) : ShopRepository {
    override suspend fun getAllProducts(): Resource<List<Product>> {
        return mapWithResource({ shopRemoteDataSource.getAllProducts() }) {
            it.map { productsDto ->
                productsDto.mapToProduct()
            }
        }
    }

    override suspend fun getProductById(productId: Int): Resource<Product> {
        return mapWithResource({ shopRemoteDataSource.getProductById(productId) }) { productsDto ->
            productsDto.mapToProduct()
        }
    }

    override suspend fun sortAllProducts(sort: String): Resource<List<ProductDto>> {
        return executeWithResource { shopRemoteDataSource.sortAllProducts(sort) }
    }

    override suspend fun getAllCategories(): Resource<List<String>> {
        return executeWithResource { shopRemoteDataSource.getAllCategories() }
    }

    override suspend fun getAllProductsByCategory(category: String): Resource<List<Product>> {
        return mapWithResource({ shopRemoteDataSource.getAllProductsByCategory(category) }) { productsDto ->
            productsDto.map { it.mapToProduct() }
        }
    }

    override suspend fun getAllCartsByUserId(userId: Int): Resource<MutableList<Cart>> {
        val cartItem = mutableListOf<Cart>()
        val productsId = mutableListOf<Int>()
        mapWithResource({ shopRemoteDataSource.getAllCartsByUserId(userId) }) { cartsDto ->
            cartsDto.map { cartDto ->
                cartDto.products.forEach { cartProductDto ->
                    productsId.add(cartProductDto.productId)
                    cartItem.add(
                        Cart(
                            date = cartDto.date,
                            id = cartDto.id,
                            cartProduct = CartProduct(
                                id = cartProductDto.productId,
                                quantity = cartProductDto.quantity,
                            ),
                            userId = cartDto.userId,
                        )
                    )
                }
            }
        }
        productsId.forEach { productId ->
            val products = shopRemoteDataSource.getProductById(productId)
            cartItem.add(
                Cart(
                    cartProduct = CartProduct(
                        category = products.category,
                        image = products.image,
                        price = products.price,
                    ),
                )
            )
        }
        return try {
            Resource.Success(cartItem)
        } catch (e: Throwable) {
            Resource.Error(e.message.toString())
        }
    }


    override suspend fun getCartById(cartId: Int): Resource<CartDto> {
        return executeWithResource { shopRemoteDataSource.getCartById(cartId) }
    }

    override suspend fun sortAllCarts(sort: String): Resource<CartDto> {
        return executeWithResource { shopRemoteDataSource.sortAllCarts(sort) }
    }

    override suspend fun getUserById(userId: Int): Resource<List<User>> {
        return mapWithResource({ shopRemoteDataSource.getUserById(userId) }) { userDto ->
            userDto.map { it.mapToUser() }
        }
    }

    private suspend fun <I, O> mapWithResource(
        function: suspend () -> I,
        mapper: (I) -> O
    ): Resource<O> {
        return try {
            Resource.Success(mapper(function()))
        } catch (e: Throwable) {
            Resource.Error(e.message)
        }
    }


    private suspend fun <T> executeWithResource(function: suspend () -> T): Resource<T> {
       return try {
           Resource.Success(function())
        } catch (e: Throwable) {
            Resource.Error(e.message)
        }
    }
}