package com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.remote.itunes.model

import com.google.gson.annotations.SerializedName
import com.saltpay.android_developer_challenge_kgfaly.feature.albums.domain.model.Artist as DomainArtist

data class Artist(
    @SerializedName("label")
    val name: String
) {
    fun toDomain(): DomainArtist {
        return DomainArtist(
            name = name
        )
    }
}