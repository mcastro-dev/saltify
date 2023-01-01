package com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.remote.itunes.model

import com.google.gson.annotations.SerializedName
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Image as DomainImage

data class Image(
    @SerializedName("label")
    val url: String,
    @SerializedName("attributes.height")
    val height: Int
) {
    fun toDomain(): DomainImage {
        return DomainImage(
            url = url,
            height = height
        )
    }
}