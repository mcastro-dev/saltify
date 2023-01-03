package com.saltpay.android_developer_challenge_kgfaly.feature.albums.data

import com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.local.AlbumsLocalDataSource
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.remote.AlbumsRemoteDataSource
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Album
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.repository.AlbumsRepository
import com.saltpay.core.domain.UnableToReachServerException
import java.net.SocketException
import java.net.UnknownHostException
import javax.inject.Inject

class AlbumsRepositoryImpl @Inject constructor(
    private val remoteDataSource: AlbumsRemoteDataSource,
    private val localDataSource: AlbumsLocalDataSource
) : AlbumsRepository {

    override suspend fun getTopAlbums(limit: Int): Result<List<Album>> {
        return try {
            val albums = remoteDataSource.getTopAlbums(limit)
            localDataSource.deleteAllTopAlbums()
            localDataSource.saveTopAlbums(albums)
            Result.success(albums)

        } catch (e: UnknownHostException) {
            Result.failure(UnableToReachServerException())
        } catch (e: SocketException) {
            Result.failure(UnableToReachServerException())
        }
    }

    override suspend fun getTopAlbumsByTitle(query: String, limit: Int): Result<List<Album>> {
        val albums = localDataSource.getTopAlbums(limit)

        val caseInsensitiveRegex = Regex("(?i).*$query.*")
        val filteredAlbums = albums.filter {
            it.title.contains(caseInsensitiveRegex)
        }

        return Result.success(filteredAlbums)
    }
}