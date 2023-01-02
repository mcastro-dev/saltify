package com.saltpay.android_developer_challenge_kgfaly.feature.albums.data.remote.itunes.model.deserializer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class ImageHeightDeserializer : JsonDeserializer<Int> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Int {
        val jsonObject = json?.asJsonObject ?: throw RuntimeException("Json should not be null")
        val height = jsonObject.get("height").asInt
        return height
    }
}