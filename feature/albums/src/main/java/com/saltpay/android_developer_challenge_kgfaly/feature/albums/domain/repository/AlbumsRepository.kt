package com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.repository

import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Album

interface AlbumsRepository {
    suspend fun getTopAlbums(limit: Int): Result<List<Album>>
    suspend fun getTopAlbumsByTitle(query: String, limit: Int): Result<List<Album>>
}