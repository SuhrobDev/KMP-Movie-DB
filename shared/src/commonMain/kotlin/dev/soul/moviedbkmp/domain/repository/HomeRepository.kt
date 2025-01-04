package dev.soul.moviedbkmp.domain.repository

import androidx.paging.PagingData
import dev.soul.moviedbkmp.data.network.dto.base.BaseMovieListResponse
import dev.soul.moviedbkmp.data.network.dto.base.BasePeopleListResponse
import dev.soul.moviedbkmp.data.network.dto.movie.MovieItemModel
import dev.soul.moviedbkmp.data.network.dto.movie.actors.MovieActorsItem
import dev.soul.moviedbkmp.data.network.dto.movie.detail.MovieDetailModel
import dev.soul.moviedbkmp.data.network.dto.people.PeopleItemModel
import dev.soul.moviedbkmp.data.network.dto.similar.SimilarMovieItem
import dev.soul.moviedbkmp.utils.NetworkError
import dev.soul.moviedbkmp.utils.Result
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getNowPlaying(): Flow<PagingData<MovieItemModel>>
    suspend fun getMovieDetail(id: Long): Result<MovieDetailModel, NetworkError>
    suspend fun getPeople(): Result<BaseMovieListResponse<PeopleItemModel>, NetworkError>
    suspend fun getMovieActors(movieId: Long): Result<BasePeopleListResponse<MovieActorsItem>, NetworkError>
    suspend fun getSimilarMovies(movieId: Long): Result<BaseMovieListResponse<SimilarMovieItem>, NetworkError>


}