package com.sajjadio.quickshop.data.repository

import com.sajjadio.quickshop.data.dataSource.ShopRemoteDataSource
import com.sajjadio.quickshop.data.remote.mapper.toProductDomain
import com.sajjadio.quickshop.data.remote.model.cart.Cart
import com.sajjadio.quickshop.data.remote.model.cart.Carts
import com.sajjadio.quickshop.data.remote.model.products.ProductDto
import com.sajjadio.quickshop.domain.model.products.Product
import com.sajjadio.quickshop.domain.repository.ShopRepository
import com.sajjadio.quickshop.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class ShopShopRepositoryImpl @Inject constructor(
    private val shopRemoteDataSource: ShopRemoteDataSource,
) : ShopRepository {
    override fun getProducts(): Flow<Resource<List<Product>>> {
        return wrapper({ shopRemoteDataSource.getProducts() }) { products ->
            products.map { it.toProductDomain() }
        }
    }

    override fun getProduct(productId: Int): Flow<Resource<ProductDto>> {
        return wrapWithFlow { shopRemoteDataSource.getProductById(productId) }
    }

    override fun sortProducts(sort: String): Flow<Resource<List<ProductDto>>> {
        return wrapWithFlow { shopRemoteDataSource.sortProducts(sort) }
    }

    override fun getCategories(): Flow<Resource<List<String>>> {
        return wrapWithFlow{shopRemoteDataSource.getCategories()}
    }

    override fun getProductByCategory(category: String): Flow<Resource<List<ProductDto>>> {
        return wrapWithFlow { shopRemoteDataSource.getProductByCategory(category) }
    }

    override fun getCarts(): Flow<Resource<Carts>> {
        return wrapWithFlow { shopRemoteDataSource.getCarts() }
    }

    override fun getCartById(cartId: Int): Flow<Resource<Cart>> {
        return wrapWithFlow { shopRemoteDataSource.getCartById(cartId) }
    }

    override fun sortCarts(sort: String): Flow<Resource<Carts>> {
        return wrapWithFlow { shopRemoteDataSource.sortCarts(sort) }
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