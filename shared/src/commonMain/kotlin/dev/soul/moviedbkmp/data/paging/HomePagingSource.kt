package dev.soul.moviedbkmp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.soul.moviedbkmp.data.network.InsultCensorClient
import dev.soul.moviedbkmp.data.network.dto.movie.MovieItemModel
import dev.soul.moviedbkmp.utils.Result
import org.koin.core.component.KoinComponent

class HomePagingSource(
    private val insultCensorClient: InsultCensorClient
) : PagingSource<Int, MovieItemModel>(), KoinComponent {
    companion object {
        private const val STARTING_PAGE_INDEX = 1
        private const val PAGE_SIZE = 30
    }

    override fun getRefreshKey(state: PagingState<Int, MovieItemModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItemModel> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            println("keldigggg2")
            when (val response = insultCensorClient.getNowPlaying(page = position)) {
                is Result.Success -> {
                    println("keldigggg7")
                    val applications = response.data.results
                    println("keldigggg8")
                    println("keldigggg8 ${if (applications.isNullOrEmpty() || applications.size < PAGE_SIZE) null else position + 1}")
                    LoadResult.Page(
                        data = applications ?: emptyList(),
                        prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                        nextKey = if (applications.isNullOrEmpty() || applications.size < PAGE_SIZE) null else position + 1
                    )
                }

                is Result.Error -> {
                    println("keldigggg8")
                    LoadResult.Error(Exception(response.error.name))
                }
            }
        } catch (exception: Exception) {
            println("keldigggg9 ${exception.message}")
            exception.printStackTrace()
            LoadResult.Error(exception)
        }
    }
}