package com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.remote.itunes.model

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.remote.itunes.model.deserializer.AlbumIdDeserializer
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.remote.itunes.model.deserializer.AlbumNameDeserializer
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Album as DomainAlbum

data class Album(
    @JsonAdapter(AlbumIdDeserializer::class)
    @SerializedName("id")
    val id: String,
    @JsonAdapter(AlbumNameDeserializer::class)
    @SerializedName("im:name")
    val name: String,
    @SerializedName("im:image")
    val images: List<Image>,
    @SerializedName("im:artist")
    val artist: Artist
) {
    fun toDomain(): DomainAlbum {
        return DomainAlbum(
            id = id,
            title = name,
            images = images.map { it.toDomain() },
            artist = artist.toDomain()
        )
    }
}