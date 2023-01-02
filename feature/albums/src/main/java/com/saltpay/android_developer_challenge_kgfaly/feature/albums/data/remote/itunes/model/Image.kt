package com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.remote.itunes.model

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.remote.itunes.model.deserializer.AlbumNameDeserializer
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.remote.itunes.model.deserializer.ImageHeightDeserializer
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Image as DomainImage

data class Image(
    @SerializedName("label")
    val url: String,
    @JsonAdapter(ImageHeightDeserializer::class)
    @SerializedName("attributes")
    val height: Int
) {
    fun toDomain(): DomainImage {
        return DomainImage(
            url = url,
            height = height
        )
    }
}