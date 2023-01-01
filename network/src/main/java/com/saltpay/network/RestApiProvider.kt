package com.saltpay.network

interface RestApiProvider {
    fun <T>provide(apiClass: Class<T>): T
}