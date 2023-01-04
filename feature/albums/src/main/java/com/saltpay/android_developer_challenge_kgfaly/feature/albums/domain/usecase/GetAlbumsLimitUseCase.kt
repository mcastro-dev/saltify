package com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.usecase

import com.saltpay.core.domain.usecase.UseCase
import javax.inject.Inject

interface GetAlbumsLimitUseCase : UseCase<Int>

class GetAlbumsLimitUseCaseImpl @Inject constructor() : GetAlbumsLimitUseCase {
    private companion object {
        const val LIMIT = 100
    }

    override fun invoke(): Int {
        return LIMIT
    }
}