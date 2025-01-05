package dev.soul.moviedbkmp.data.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.soul.moviedbkmp.data.network.InsultCensorClient
import dev.soul.moviedbkmp.data.network.dto.base.BaseMovieListResponse
import dev.soul.moviedbkmp.data.network.dto.base.BasePeopleListResponse
import dev.soul.moviedbkmp.data.network.dto.movie.MovieItemModel
import dev.soul.moviedbkmp.data.network.dto.movie.actors.MovieActorsItem
import dev.soul.moviedbkmp.data.network.dto.movie.detail.MovieDetailModel
import dev.soul.moviedbkmp.data.network.dto.people.PeopleItemModel
import dev.soul.moviedbkmp.data.network.dto.similar.SimilarMovieItem
import dev.soul.moviedbkmp.data.paging.HomePagingSource
import dev.soul.moviedbkmp.domain.repository.HomeRepository
import dev.soul.moviedbkmp.utils.NetworkError
import dev.soul.moviedbkmp.utils.Result
import kotlinx.coroutines.flow.Flow

class HomeRepositoryImpl(
    private val client: InsultCensorClient
) : HomeRepository {

    override suspend fun getNowPlaying(): Flow<PagingData<MovieItemModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30,
                initialLoadSize = 10,
                enablePlaceholders = true,
            ), pagingSourceFactory = {
                HomePagingSource(
                    insultCensorClient = client
                )
            }).flow
    }

    override suspend fun getMovieDetail(id: Long): Result<MovieDetailModel, NetworkError> {
        return client.getMovieDetail(id)
    }

    override suspend fun getPeople(): Result<BaseMovieListResponse<PeopleItemModel>, NetworkError> {
        return client.getPeople()
    }

    override suspend fun getMovieActors(movieId: Long): Result<BasePeopleListResponse<MovieActorsItem>, NetworkError> {
        return client.getMovieActors(movieId)
    }

    override suspend fun getSimilarMovies(movieId: Long): Result<BaseMovieListResponse<SimilarMovieItem>, NetworkError> {
        return client.getSimilarMovies(movieId)
    }

    override suspend fun getNowPlayingMovies(): Result<BaseMovieListResponse<MovieItemModel>, NetworkError> {
        return client.getNowPlaying(1)
    }
    override suspend fun getPopularMovies(): Result<BaseMovieListResponse<MovieItemModel>, NetworkError> {
        return client.getPopular(1)
    }
}