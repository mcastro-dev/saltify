package com.saltpay.core.domain.usecase

interface UseCase<T> {
    operator fun invoke(): T
}

interface CoroutineUseCase<T> {
    suspend operator fun invoke(): Result<T>
}

interface ParameterizedCoroutineUseCase<Params, T> {
    suspend operator fun invoke(params: Params): Result<T>
}