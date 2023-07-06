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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class ShopShopRepositoryImpl @Inject constructor(
    private val shopRemoteDataSource: ShopRemoteDataSource,
) : ShopRepository {
    override fun getAllProducts(): Flow<Resource<List<Product>>> {
        return wrapper({ shopRemoteDataSource.getAllProducts() }) { productsDto ->
            productsDto.map { it.mapToProduct() }
        }
    }

    override fun getProductById(productId: Int): Flow<Resource<Product>> {
        return wrapper({ shopRemoteDataSource.getProductById(productId) }) { productsDto ->
            productsDto.mapToProduct()
        }
    }

    override fun sortAllProducts(sort: String): Flow<Resource<List<ProductDto>>> {
        return wrapWithFlow { shopRemoteDataSource.sortAllProducts(sort) }
    }

    override fun getAllCategories(): Flow<Resource<List<String>>> {
        return wrapWithFlow { shopRemoteDataSource.getAllCategories() }
    }

    override fun getAllProductsByCategory(category: String): Flow<Resource<List<Product>>> {
        return wrapper({ shopRemoteDataSource.getAllProductsByCategory(category) }) { productsDto ->
            productsDto.map { it.mapToProduct() }
        }
    }

    override suspend fun getAllCartsByUserId(userId: Int): Flow<Resource<MutableList<Cart>>> {
        return flow {
            try {
                emit(Resource.Loading)
                val cartItem = mutableListOf<Cart>()
                val response = shopRemoteDataSource.getAllCartsByUserId(userId)
                if (response.isSuccessful) {
                    response.body()?.forEach { cartDto ->
                        cartDto.products.forEach { cartProductDto ->
                            val products =
                                shopRemoteDataSource
                                    .getProductById(cartProductDto.productId)
                                    .body()
                            cartItem.add(
                                Cart(
                                    date = cartDto.date,
                                    id = cartDto.id,
                                    cartProduct = CartProduct(
                                        category = products?.category.toString(),
                                        id = cartProductDto.productId,
                                        image = products?.image.toString(),
                                        price = products?.price!!,
                                        quantity = cartProductDto.quantity,
                                    ),
                                    userId = cartDto.userId,
                                )
                            )
                        }
                    }
                    emit(Resource.Success(cartItem))
                } else {
                    emit(Resource.Error(response.message().toString()))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    override fun getCartById(cartId: Int): Flow<Resource<CartDto>> {
        return wrapWithFlow { shopRemoteDataSource.getCartById(cartId) }
    }

    override fun sortAllCarts(sort: String): Flow<Resource<CartDto>> {
        return wrapWithFlow { shopRemoteDataSource.sortAllCarts(sort) }
    }

    override fun getUserById(userId: Int): Flow<Resource<List<User>>> {
        return wrapper({ shopRemoteDataSource.getUserById(userId) }) { userDto ->
            userDto.map { it.mapToUser() }
        }
    }

    private fun <I, O> wrapper(
        function: suspend () -> Response<I>,
        mapper: (I) -> O
    ): Flow<Resource<O>> {
        return flow {
            try {
                emit(Resource.Loading)
                val response = function()
                if (response.isSuccessful) {
                    emit(Resource.Success(response.body()?.let { mapper(it) }))
                } else {
                    emit(Resource.Error(response.message()))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    private fun <T> wrapWithFlow(function: suspend () -> Response<T>): Flow<Resource<T>> {
        return flow {
            try {
                emit(Resource.Loading)
                emit(checkIsSuccessfulResponse(function.invoke()))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    private fun <T> checkIsSuccessfulResponse(response: Response<T>): Resource<T> {
        return if (response.isSuccessful)
            Resource.Success(response.body())
        else
            Resource.Error(response.message())
    }
}