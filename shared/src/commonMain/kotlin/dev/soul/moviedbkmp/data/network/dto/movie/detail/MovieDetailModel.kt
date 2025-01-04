package dev.soul.moviedbkmp.data.network.dto.movie.detail

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailModel(
    @SerialName("adult")
    val adult: Boolean? = null,
    @SerialName("backdrop_path")
    val backdrop_path: String? = null,
    @SerialName("belongs_to_collection")
    val belongs_to_collection: BelongToCollection? = null,
    @SerialName("budget")
    val budget: Int? = null,
    @SerialName("genres")
    val genres: List<Genre>? = emptyList(),
    @SerialName("homepage")
    val homepage: String? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("imdb_id")
    val imdb_id: String? = null,
    @SerialName("origin_country")
    val origin_country: List<String>? = emptyList(),
    @SerialName("original_language")
    val original_language: String? = null,
    @SerialName("original_title")
    val original_title: String? = null,
    @SerialName("overview")
    val overview: String? = null,
    @SerialName("popularity")
    val popularity: Double? = null,
    @SerialName("poster_path")
    val poster_path: String? = null,
    @SerialName("production_companies")
    val production_companies: List<ProductionCompany>? = emptyList(),
    @SerialName("production_countries")
    val production_countries: List<ProductionCountry>? = emptyList(),
    @SerialName("release_date")
    val release_date: String? = null,
    @SerialName("revenue")
    val revenue: Int? = null,
    @SerialName("runtime")
    val runtime: Int? = null,
    @SerialName("spoken_languages")
    val spoken_languages: List<SpokenLanguage>? = emptyList(),
    @SerialName("status")
    val status: String? = null,
    @SerialName("tagline")
    val tagline: String? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("video")
    val video: Boolean? = null,
    @SerialName("vote_average")
    val vote_average: Double? = null,
    @SerialName("vote_count")
    val vote_count: Int? = null
){
    val backdropUrl: String
        get()="https://image.tmdb.org/t/p/original$backdrop_path"



    val posterUrl: String
        get() = "https://image.tmdb.org/t/p/original$poster_path"
}