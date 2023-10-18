package com.example.mymovieappjc.data.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mymovieappjc.domain.model.MovieData

class SearchMoviePagingSource(
    private val movieApi: MovieApi,
    private val searchQuery: String,
): PagingSource<Int, MovieData>(){

    private var totalMovieCount = 0
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieData> {
        val page = params.key ?: 1
        Log.e("Search*****", ""+ searchQuery)
        return try {
            Log.e("Search*****", ""+ searchQuery)
            val movieResponse = movieApi.getSearchMovies(page = page, searchKey = searchQuery)
            totalMovieCount += movieResponse.results?.size!!
            Log.e("Search*****", ""+ page)
            val movies = movieResponse.results.distinctBy { it.title } // Remove Duplicate
            LoadResult.Page(
                data = movies,
                nextKey = if (totalMovieCount == movieResponse.total_results) null else page + 1,
                prevKey = null
            )
        }catch (e: Exception){
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
        Log.e("Search*****", ""+ searchQuery)
    }

    override fun getRefreshKey(state: PagingState<Int, MovieData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}