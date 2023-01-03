package com.saltpay.core.infrastructure

interface RestApiProvider {
    fun <T>provide(apiClass: Class<T>): T
}