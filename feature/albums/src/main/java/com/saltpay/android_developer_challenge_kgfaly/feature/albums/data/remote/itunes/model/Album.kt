package com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.remote.itunes.model

import com.google.gson.annotations.SerializedName
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Album as DomainAlbum

data class Album(
    @SerializedName("im:name.label")
    val name: String,
    @SerializedName("im:image")
    val images: List<Image>,
    @SerializedName("im:artist")
    val artist: Artist
) {
    fun toDomain(): DomainAlbum {
        return DomainAlbum(
            title = name,
            images = images.map { it.toDomain() },
            artist = artist.toDomain()
        )
    }
}