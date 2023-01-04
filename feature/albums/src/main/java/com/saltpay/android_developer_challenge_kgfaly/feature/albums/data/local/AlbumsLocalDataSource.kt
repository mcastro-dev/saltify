package com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.local

import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Album

interface AlbumsLocalDataSource {
    suspend fun getTopAlbums(limit: Int): List<Album>
    suspend fun getAlbumById(id: String): Album?
    suspend fun saveTopAlbums(albums: List<Album>)
    suspend fun deleteAllTopAlbums()
}