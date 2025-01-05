package dev.soul.moviedbkmp.data.network

import dev.soul.moviedbkmp.data.network.dto.base.BaseMovieListResponse
import dev.soul.moviedbkmp.data.network.dto.base.BasePeopleListResponse
import dev.soul.moviedbkmp.data.network.dto.movie.MovieItemModel
import dev.soul.moviedbkmp.data.network.dto.movie.actors.MovieActorsItem
import dev.soul.moviedbkmp.data.network.dto.movie.detail.MovieDetailModel
import dev.soul.moviedbkmp.data.network.dto.people.PeopleItemModel
import dev.soul.moviedbkmp.data.network.dto.similar.SimilarMovieItem
import dev.soul.moviedbkmp.utils.NetworkError
import dev.soul.moviedbkmp.utils.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException

class InsultCensorClient(
    private val httpClient: HttpClient
) {
    val BASE_URL = "https://api.themoviedb.org/3/"
    val IMAGE = "https://image.tmdb.org/t/p/original/"
    val NOW_PLAYING = "${BASE_URL}movie/now_playing"
    val POPULAR = "${BASE_URL}movie/popular"

    suspend fun getNowPlaying(page: Int): Result<BaseMovieListResponse<MovieItemModel>, NetworkError> {
        val response = try {
            httpClient.get(
                urlString = "$NOW_PLAYING"
            ) {

                parameter("language", "en-US")
                parameter("page", page)
                parameter("api_key", "8454fdb3aa03f088967727c3a3f714e5")

            }
        } catch (e: UnresolvedAddressException) {
            e.printStackTrace()
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }
        return when (response.status.value) {
            in 200..299 -> {
                val data = response.body<BaseMovieListResponse<MovieItemModel>>()
                Result.Success(data)
            }

            400 -> Result.Error(NetworkError.INVALID_PARAMETER)
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.CONFLICT)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }


    suspend fun getPopular(page: Int): Result<BaseMovieListResponse<MovieItemModel>, NetworkError> {
        val response = try {
            httpClient.get(
                urlString = "$POPULAR"
            ) {
                parameter("language", "en-US")
                parameter("page", page)
                parameter("api_key", "8454fdb3aa03f088967727c3a3f714e5")
            }
        } catch (e: UnresolvedAddressException) {
            e.printStackTrace()
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }
        return when (response.status.value) {
            in 200..299 -> {
                val data = response.body<BaseMovieListResponse<MovieItemModel>>()
                Result.Success(data)
            }

            400 -> Result.Error(NetworkError.INVALID_PARAMETER)
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.CONFLICT)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }

    suspend fun getMovieDetail(id: Long): Result<MovieDetailModel, NetworkError> {
        val response = try {
            httpClient.get(
                urlString = "${BASE_URL}movie/$id"
            ) {
                parameter("language", "en-US")
                parameter("api_key", "8454fdb3aa03f088967727c3a3f714e5")
            }
        } catch (e: UnresolvedAddressException) {
            e.printStackTrace()
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }
        return when (response.status.value) {
            in 200..299 -> {
                val data = response.body<MovieDetailModel>()
                Result.Success(data)
            }

            400 -> Result.Error(NetworkError.INVALID_PARAMETER)
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.CONFLICT)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }

    suspend fun getPeople(): Result<BaseMovieListResponse<PeopleItemModel>, NetworkError> {
        val response = try {
            httpClient.get(
                urlString = "${BASE_URL}trending/person/day"
            ) {
                parameter("language", "en-US")
//                parameter("page", 1)
                parameter("api_key", "8454fdb3aa03f088967727c3a3f714e5")
            }
        } catch (e: UnresolvedAddressException) {
            e.printStackTrace()
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when (response.status.value) {
            in 200..299 -> {
                val data = response.body<BaseMovieListResponse<PeopleItemModel>>()
                Result.Success(data)
            }

            400 -> Result.Error(NetworkError.INVALID_PARAMETER)
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.CONFLICT)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }


    suspend fun getMovieActors(movieId: Long): Result<BasePeopleListResponse<MovieActorsItem>, NetworkError> {
        val response = try {
            httpClient.get(
                urlString = "${BASE_URL}movie/$movieId/credits"
            ) {
                parameter("language", "en-US")
                parameter("api_key", "8454fdb3aa03f088967727c3a3f714e5")
            }
        } catch (e: UnresolvedAddressException) {
            e.printStackTrace()
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }
        return when (response.status.value) {
            in 200..299 -> {
                val data = response.body<BasePeopleListResponse<MovieActorsItem>>()
                Result.Success(data)
            }

            400 -> Result.Error(NetworkError.INVALID_PARAMETER)
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.CONFLICT)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }

    suspend fun getSimilarMovies(movieId: Long): Result<BaseMovieListResponse<SimilarMovieItem>, NetworkError> {
        val response = try {
            httpClient.get(
                urlString = "${BASE_URL}movie/$movieId/similar"
            ) {
                parameter("language", "en-US")
                parameter("page", 1)
                parameter("api_key", "8454fdb3aa03f088967727c3a3f714e5")
            }

        } catch (e: UnresolvedAddressException) {
            e.printStackTrace()
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }
        return when (response.status.value) {
            in 200..299 -> {
                val data = response.body<BaseMovieListResponse<SimilarMovieItem>>()
                Result.Success(data)
            }

            400 -> Result.Error(NetworkError.INVALID_PARAMETER)
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.CONFLICT)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }

}