package com.saltpay.android_developer_challenge_kgfaly.feature.albums.data

import com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.remote.AlbumsRemoteDataSource
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Album
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.repository.AlbumsRepository
import kotlinx.coroutines.flow.Flow

class AlbumsRepositoryImpl(
    private val remoteDataSource: AlbumsRemoteDataSource
) : AlbumsRepository {
    override fun getTopAlbums(): Flow<Result<List<Album>>> {
        TODO("Not yet implemented")
    }
}