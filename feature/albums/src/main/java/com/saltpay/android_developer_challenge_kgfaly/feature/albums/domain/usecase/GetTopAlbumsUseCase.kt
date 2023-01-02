package com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.usecase

import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Album
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.repository.AlbumsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetTopAlbumsUseCase {
    suspend operator fun invoke(): Result<List<Album>>
}

class GetTopAlbumsUseCaseImpl @Inject constructor(
    private val albumsRepository: AlbumsRepository
) : GetTopAlbumsUseCase {

    companion object {
        const val LIMIT = 100
    }

    override suspend fun invoke(): Result<List<Album>> {
        return albumsRepository.getTopAlbums(LIMIT)
    }
}