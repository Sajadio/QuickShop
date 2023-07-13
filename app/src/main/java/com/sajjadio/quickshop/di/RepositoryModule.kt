package com.sajjadio.quickshop.di

import com.sajjadio.quickshop.data.repository.ShopRepositoryImpl
import com.sajjadio.quickshop.domain.repository.ShopRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindShopRepository(shopRepositoryImpl: ShopRepositoryImpl): ShopRepository
}