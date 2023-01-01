package com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.remote.itunes.model

import com.google.gson.annotations.SerializedName

data class Feed(
    @SerializedName("entry")
    val albums: List<Album>
)