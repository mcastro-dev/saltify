package com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.local.inmemory

import com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.local.AlbumsLocalDataSource
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Album
import javax.inject.Inject

class InMemoryAlbumsDataSource @Inject constructor() : AlbumsLocalDataSource {

    internal val topAlbums: MutableList<Album> = mutableListOf()

    override suspend fun getTopAlbums(limit: Int): List<Album> {
        return topAlbums.take(limit)
    }

    override suspend fun saveTopAlbums(albums: List<Album>) {
        topAlbums.addAll(albums)
    }

    override suspend fun deleteAllTopAlbums() {
        topAlbums.clear()
    }
}