package dev.soul.moviedbkmp.data.network.dto.movie.detail

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    @SerialName("id")
    val id: Int ?=null,
    @SerialName("name")
    val name: String ?=null
)