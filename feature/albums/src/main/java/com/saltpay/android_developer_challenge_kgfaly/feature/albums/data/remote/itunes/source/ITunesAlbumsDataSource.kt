package com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.remote.itunes.source

import com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.remote.AlbumsRemoteDataSource
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Album
import javax.inject.Inject

class ITunesAlbumsDataSource @Inject constructor(
    private val albumsApi: ITunesAlbumsApi
) : AlbumsRemoteDataSource {

    override suspend fun getTopAlbums(limit: Int): List<Album> {
        val response = albumsApi.getTopAlbumsFeed(limit = limit)
        return response.feed.albums.map {
            it.toDomain()
        }
    }
}