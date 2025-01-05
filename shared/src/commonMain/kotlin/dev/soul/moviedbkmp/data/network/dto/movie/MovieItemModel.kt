package dev.soul.moviedbkmp.data.network.dto.movie

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieItemModel(
    @SerialName("adult")
    val adult: Boolean?=null,
    @SerialName("backdrop_path")
    val backdrop_path: String?=null,
    @SerialName("genre_ids")
    val genre_ids: List<Int>?= emptyList(),
    @SerialName("id")
    val id: Long,
    @SerialName("original_language")
    val original_language: String?=null,
    @SerialName("original_title")
    val original_title: String?=null,
    @SerialName("overview")
    val overview: String?=null,
    @SerialName("popularity")
    val popularity: Double?=null,
    @SerialName("poster_path")
    val poster_path: String?=null,
    @SerialName("release_date")
    val release_date: String?=null,
    @SerialName("title")
    val title: String?=null,
    @SerialName("video")
    val video: Boolean?=null,
    @SerialName("vote_average")
    val vote_average: Double?=null,
    @SerialName("vote_count")
    val vote_count: Long?=null
){
    val orgImage: String
        get() = "https://image.tmdb.org/t/p/original$poster_path"

    val imageUrl: String
        get() = "https://image.tmdb.org/t/p/w500$poster_path"

}