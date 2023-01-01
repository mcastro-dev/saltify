package com.saltpay.android_developer_challenge_kgfaly.feature.albums.data

import com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.remote.AlbumsRemoteDataSource
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.repository.AlbumsRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AlbumsRepositoryImpl @Inject constructor(
    private val remoteDataSource: AlbumsRemoteDataSource
) : AlbumsRepository {

    override fun getTopAlbums(limit: Int) = flow {
        // TODO: investigate if there are any errors that should be handled here
        val albums = remoteDataSource.getTopAlbums(limit)
        emit(Result.success(albums))
    }
}