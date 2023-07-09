package com.sajjadio.quickshop.di

import com.sajjadio.quickshop.data.dataSource.ShopRemoteDataSource
import com.sajjadio.quickshop.data.dataSource.remote.ShopApiService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindsShopRemoteDataSource(
        shopApiService: ShopApiService
    ): ShopRemoteDataSource
}