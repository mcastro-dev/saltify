package com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model

data class Album(
    val title: String,
    val images: List<Image>,
    val artist: Artist
)