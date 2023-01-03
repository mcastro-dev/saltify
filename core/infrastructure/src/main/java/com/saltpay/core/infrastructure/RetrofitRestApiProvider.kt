package com.saltpay.core.infrastructure

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitRestApiProvider(
    private val url: String
) : RestApiProvider {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    override fun <T> provide(apiClass: Class<T>): T {
        return retrofit.create(apiClass)
    }

}