package dev.maxsiomin.prodhse.feature.home.data.dto.places_nearby


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
internal data class Location(
    @SerialName("address")
    val address: String? = null,
    @SerialName("address_extended")
    val addressExtended: String? = null,
    @SerialName("census_block")
    val censusBlock: String? = null,
    @SerialName("country")
    val country: String? = null,
    @SerialName("cross_street")
    val crossStreet: String? = null,
    @SerialName("dma")
    val dma: String? = null,
    @SerialName("formatted_address")
    val formattedAddress: String? = null,
    @SerialName("locality")
    val locality: String? = null,
    @SerialName("neighborhood")
    val neighborhood: List<String?>? = null,
    @SerialName("postcode")
    val postcode: String? = null,
    @SerialName("region")
    val region: String? = null
)