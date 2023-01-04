package com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.usecase

import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Album
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.repository.AlbumsRepository
import com.saltpay.core.domain.usecase.ParameterizedCoroutineUseCase
import javax.inject.Inject

interface GetAlbumByIdUseCase : ParameterizedCoroutineUseCase<GetAlbumByIdUseCase.Params, Album> {
    data class Params(
        val albumId: String
    )
}

class GetAlbumByIdUseCaseImpl @Inject constructor(
    private val albumsRepository: AlbumsRepository
) : GetAlbumByIdUseCase {

    override suspend fun invoke(params: GetAlbumByIdUseCase.Params): Result<Album> {
        val id = params.albumId
        return albumsRepository.getAlbumById(id)
    }
}