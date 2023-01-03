package com.saltpay.core.domain

interface UseCase {
    operator fun invoke()
}

interface CoroutineUseCase<T> {
    suspend operator fun invoke(): Result<T>
}

interface ParameterizedCoroutineUseCase<Params, T> {
    suspend operator fun invoke(params: Params): Result<T>
}