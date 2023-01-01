package com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.usecase

import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Album
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.repository.AlbumsRepository
import kotlinx.coroutines.flow.Flow

interface GetTopAlbumsUseCase {
    fun invoke(): Flow<Result<List<Album>>>
}

class GetTopAlbumsUseCaseImpl(
    private val albumsRepository: AlbumsRepository
) : GetTopAlbumsUseCase {

    override fun invoke(): Flow<Result<List<Album>>> {
        TODO("Not yet implemented")
    }
}