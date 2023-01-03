package com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.usecase

import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Album
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.repository.AlbumsRepository
import com.saltpay.core.domain.CoroutineUseCase
import javax.inject.Inject

interface RefreshTopAlbumsUseCase : CoroutineUseCase<List<Album>>

class RefreshTopAlbumsUseCaseImpl @Inject constructor(
    private val albumsRepository: AlbumsRepository
) : RefreshTopAlbumsUseCase {

    override suspend fun invoke(): Result<List<Album>> {
        return albumsRepository.getTopAlbums()
    }
}