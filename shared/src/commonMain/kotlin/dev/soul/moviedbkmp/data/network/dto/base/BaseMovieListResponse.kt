package dev.soul.moviedbkmp.data.network.dto.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseMovieListResponse<T>(
    @SerialName("dates")
    val dates: Dates ?= null,
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: List<T> ?= emptyList(),
    @SerialName("total_pages")
    val total_pages: Int ?= 0,
    @SerialName("total_results")
    val total_results: Int ?= 0
)

@Serializable
data class BasePeopleListResponse<T>(
    @SerialName("id")
    val id: Long,
    @SerialName("results")
    val results: List<T> ?= emptyList(),
)
