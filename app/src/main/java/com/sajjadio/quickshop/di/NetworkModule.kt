package com.sajjadio.quickshop.di

import com.sajjadio.quickshop.data.remote.ShopApiService
import com.sajjadio.quickshop.data.remote.interceptor.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providerRetrofit(retrofit: Retrofit): ShopApiService {
        return retrofit.create(ShopApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        @Named("Shop_Base_Url") baseUrl: String,
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit
            .Builder()
            .apply {
                baseUrl(baseUrl)
                addConverterFactory(gsonConverterFactory)
                client(okHttpClient)
            }.build()
    }

    @Provides
    @Singleton
    @Named("Shop_Base_Url")
    fun provideBaseUrlOfShop(): String = "https://fakestoreapi.com/"

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
    ): OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(authInterceptor)
        readTimeout(100L, TimeUnit.SECONDS)
        connectTimeout(100L, TimeUnit.SECONDS)
        writeTimeout(100L, TimeUnit.SECONDS)
    }.build()

}