package com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.remote.itunes.source

import com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.remote.itunes.model.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ITunesAlbumsApi {

    @GET("/us/rss/topalbums/limit={limit}/json")
    suspend fun getTopAlbumsFeed(
        @Path("limit") limit: Int,
    ): Response
}