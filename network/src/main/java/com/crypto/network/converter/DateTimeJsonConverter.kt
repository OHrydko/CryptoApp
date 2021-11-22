package com.crypto.network.converter

import com.google.gson.*
import org.threeten.bp.Instant
import org.threeten.bp.format.DateTimeFormatter
import java.lang.reflect.Type
import javax.inject.Inject

class DateTimeJsonConverter @Inject internal constructor() : JsonSerializer<Instant>,
    JsonDeserializer<Instant> {

    override fun serialize(src: Instant, typeOfSrc: Type, context: JsonSerializationContext)
            : JsonElement {

        return JsonPrimitive(src.toString())
    }

    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement, typeOfT: Type,
        context: JsonDeserializationContext
    ): Instant? {

        // Do not try to deserialize null or empty values
        if (json.asString == null || json.asString.isEmpty()) {
            return null
        }
//        ISO 860
        return try {
            Instant.parse(json.asString)
        } catch (e: Exception) {
            Instant.from(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").parse(json.asString))
        }
    }
}