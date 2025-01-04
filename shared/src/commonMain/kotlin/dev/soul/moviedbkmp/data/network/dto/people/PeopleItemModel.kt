package dev.soul.moviedbkmp.data.network.dto.people

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PeopleItemModel(
    @SerialName("adult")
    val adult: Boolean ?= null,
    @SerialName("gender")
    val gender: Int ?= null,
    @SerialName("id")
    val id: Int ?= null,
    @SerialName("known_for_department")
    val known_for_department: String ?= null,
    @SerialName("media_type")
    val media_type: String ?= null,
    @SerialName("name")
    val name: String ?=null,
    @SerialName("original_name")
    val original_name: String ?= null,
    @SerialName("popularity")
    val popularity: Double ?= null,
    @SerialName("profile_path")
    val profile_path: String ?= null
){
    val imageUrl: String
        get() = "https://image.tmdb.org/t/p/w500$profile_path"

}