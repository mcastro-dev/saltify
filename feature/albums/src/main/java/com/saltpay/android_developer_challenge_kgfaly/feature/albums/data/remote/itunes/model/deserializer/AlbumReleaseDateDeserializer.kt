package com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.remote.itunes.model.deserializer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZonedDateTime

class AlbumReleaseDateDeserializer : JsonDeserializer<LocalDate> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): LocalDate {
        val jsonObject = json?.asJsonObject ?: throw RuntimeException("Json should not be null")
        val dateText = jsonObject.get("label").asString
        val dateTime = ZonedDateTime.parse(dateText)
        return dateTime.toLocalDate()
    }
}