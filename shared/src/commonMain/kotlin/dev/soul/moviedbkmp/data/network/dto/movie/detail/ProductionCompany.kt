package dev.soul.moviedbkmp.data.network.dto.movie.detail

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductionCompany(
    @SerialName("id")
    val id: Int ?=null,
    @SerialName("logo_path")
    val logo_path: String ?=null,
    @SerialName("name")
    val name: String ?=null,
    @SerialName("origin_country")
    val origin_country: String ?=null
)