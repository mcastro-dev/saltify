package com.saltpay.android_developer_challenge_kgfaly.feature.albums.data

import com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.remote.AlbumsRemoteDataSource
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Album
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.repository.AlbumsRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AlbumsRepositoryImpl @Inject constructor(
    private val remoteDataSource: AlbumsRemoteDataSource
) : AlbumsRepository {

    override suspend fun getTopAlbums(limit: Int): Result<List<Album>> {
        // TODO: investigate if there are any errors that should be handled here
        val albums = remoteDataSource.getTopAlbums(limit)
        return Result.success(albums)
    }
}