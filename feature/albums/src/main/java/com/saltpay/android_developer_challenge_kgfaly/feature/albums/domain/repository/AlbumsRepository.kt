package com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.repository

import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Album
import kotlinx.coroutines.flow.Flow

interface AlbumsRepository {
    suspend fun getTopAlbums(limit: Int = 100): Result<List<Album>>
}