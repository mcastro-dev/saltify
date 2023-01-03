package com.saltpay.android_developer_challenge_kgfaly.feature.albums.data

import com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.remote.AlbumsRemoteDataSource
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Album
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.repository.AlbumsRepository
import com.saltpay.core.domain.UnableToReachServerException
import java.net.SocketException
import java.net.UnknownHostException
import javax.inject.Inject

class AlbumsRepositoryImpl @Inject constructor(
    private val remoteDataSource: AlbumsRemoteDataSource
) : AlbumsRepository {

    override suspend fun getTopAlbums(limit: Int): Result<List<Album>> {
        return try {
            val albums = remoteDataSource.getTopAlbums(limit)
            Result.success(albums)

        } catch (e: UnknownHostException) {
            Result.failure(UnableToReachServerException())
        } catch (e: SocketException) {
            Result.failure(UnableToReachServerException())
        }
    }
}