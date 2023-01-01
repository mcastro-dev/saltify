package com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.remote.itunes.source

import com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.remote.AlbumsRemoteDataSource
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Album

class iTunesAlbumsDataSource(
    private val albumsApi: iTunesAlbumsApi
) : AlbumsRemoteDataSource {

    override suspend fun getTopAlbums(limit: Int): List<Album> {
        val feed = albumsApi.getTopAlbumsFeed(limit = limit)
        return feed.albums.map {
            it.toDomain()
        }
    }
}