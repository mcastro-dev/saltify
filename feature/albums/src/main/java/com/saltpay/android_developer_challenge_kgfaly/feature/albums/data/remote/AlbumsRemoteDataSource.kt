package com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.remote

import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Album

interface AlbumsRemoteDataSource {
    suspend fun getTopAlbums(limit: Int): List<Album>
}