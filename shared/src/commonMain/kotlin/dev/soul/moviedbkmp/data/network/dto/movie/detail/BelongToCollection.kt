package dev.soul.moviedbkmp.data.network.dto.movie.detail

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BelongToCollection(
    @SerialName("backdrop_path")
    val backdrop_path: String ?=null,
    @SerialName("id")
    val id: Int ?=null,
    @SerialName("name")
    val name: String ?=null,
    @SerialName("poster_path")
    val poster_path: String ?=null
)