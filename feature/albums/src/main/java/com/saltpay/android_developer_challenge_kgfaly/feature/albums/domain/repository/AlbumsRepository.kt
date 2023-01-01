package com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.repository

import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Album
import kotlinx.coroutines.flow.Flow

interface AlbumsRepository {
    fun getTopAlbums(): Flow<Result<List<Album>>>
}