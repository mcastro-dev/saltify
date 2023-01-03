package com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.usecase

import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Album
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.repository.AlbumsRepository
import com.saltpay.core.domain.ParameterizedCoroutineUseCase
import javax.inject.Inject

interface SearchTopAlbumsUseCase : ParameterizedCoroutineUseCase<SearchTopAlbumsUseCase.Params, List<Album>> {
    data class Params(
        val searchTerm: String
    )
}

class SearchTopAlbumsUseCaseImpl @Inject constructor(
    private val albumsRepository: AlbumsRepository
) : SearchTopAlbumsUseCase {

    override suspend fun invoke(params: SearchTopAlbumsUseCase.Params): Result<List<Album>> {
        val searchTerm = params.searchTerm
        return albumsRepository.getTopAlbumsByTitle(searchTerm)
    }
}