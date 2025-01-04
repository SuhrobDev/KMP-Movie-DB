package dev.soul.moviedbkmp.data.network.dto.movie.actors

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieActorsItem(
    @SerialName("id")
    val id: String ?= null,
    @SerialName("ios_3166_1")
    val iso_3166_1: String ?= null,
    @SerialName("ios_639_1")
    val iso_639_1: String ?=null,
    @SerialName("key")
    val key: String ?= null,
    @SerialName("name")
    val name: String ?= null,
    @SerialName("official")
    val official: Boolean ?= null,
    @SerialName("published_at")
    val published_at: String ?= null,
    @SerialName("site")
    val site: String ?= null,
    @SerialName("size")
    val size: Int ?= null,
    @SerialName("type")
    val type: String ?= null
)