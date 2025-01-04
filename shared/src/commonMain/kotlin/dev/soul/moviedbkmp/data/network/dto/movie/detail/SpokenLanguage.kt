package dev.soul.moviedbkmp.data.network.dto.movie.detail

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpokenLanguage(
    @SerialName("english_name")
    val english_name: String ?=null,
    @SerialName("iso_639_1")
    val iso_639_1: String?=null,
    @SerialName("name")
    val name: String?=null
)