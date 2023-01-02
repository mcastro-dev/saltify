package com.saltpay.network

import com.saltpay.android_developer_challenge_kgfaly.network.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitRestApiProvider(
    private val url: String
) : RestApiProvider {

    private val retrofit: Retrofit by lazy {
        val clientBuilder = OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            }
        }

        Retrofit.Builder()
            .baseUrl(url)
            .client(clientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    override fun <T> provide(apiClass: Class<T>): T {
        return retrofit.create(apiClass)
    }

}