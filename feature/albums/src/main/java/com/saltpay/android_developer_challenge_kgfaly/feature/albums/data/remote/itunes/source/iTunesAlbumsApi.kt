package com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.remote.itunes.source

import com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.remote.itunes.model.Feed
import retrofit2.http.GET
import retrofit2.http.Path

interface iTunesAlbumsApi {

    @GET("/us/rss/topalbums/limit={limit}/json")
    suspend fun getTopAlbumsFeed(
        @Path("limit") limit: Int,
    ): Feed
}