package com.saltpay.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RestApiProviderModule {
    @Provides
    fun provideRestApiProvider(): RestApiProvider {
        return RetrofitRestApiProvider("https://itunes.apple.com")
    }
}