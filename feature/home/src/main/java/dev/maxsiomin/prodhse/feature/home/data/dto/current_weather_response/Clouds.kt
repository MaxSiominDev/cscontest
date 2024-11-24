package dev.maxsiomin.prodhse.feature.home.data.dto.current_weather_response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
internal data class Clouds(
    @SerialName("all")
    val all: Int? = null,
)