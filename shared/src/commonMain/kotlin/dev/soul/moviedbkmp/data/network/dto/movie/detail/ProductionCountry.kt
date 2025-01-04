package dev.soul.moviedbkmp.data.network.dto.movie.detail

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductionCountry(
    @SerialName("iso_3166_1")
    val iso_3166_1: String ?=null,
    @SerialName("name")
    val name: String ?=null
)