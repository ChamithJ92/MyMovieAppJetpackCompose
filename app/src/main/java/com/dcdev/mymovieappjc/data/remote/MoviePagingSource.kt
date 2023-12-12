package com.dcdev.mymovieappjc.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dcdev.mymovieappjc.domain.model.MovieData

class MoviePagingSource(
    private val movieApi: MovieApi
): PagingSource<Int, MovieData>() {

    private var totalMovieCount = 0
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieData> {
        val page = params.key ?: 1
        return try {
            val movieResponse = movieApi.getPopularMovie(page = page)
            totalMovieCount += movieResponse.results?.size!!
            val movies = movieResponse.results.distinctBy { it.title } // Remove Duplicate
            LoadResult.Page(
                data = movies,
                nextKey = if (totalMovieCount == movieResponse.total_results) null else page + 1,
                prevKey = null
            )
        }catch (e: Exception){
            //e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}