package com.example.mymovieappjc.data.remote.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.mymovieappjc.data.remote.MovieApi
import com.example.mymovieappjc.data.remote.MoviePagingSource
import com.example.mymovieappjc.data.remote.SearchMoviePagingSource
import com.example.mymovieappjc.domain.model.MovieData
import com.example.mymovieappjc.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(
    private val movieApi: MovieApi
): MovieRepository {
    override fun getPopularMovies(): Flow<PagingData<MovieData>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                MoviePagingSource(
                    movieApi = movieApi
                )
            }
        ).flow
    }

    override fun getSearchMovies(searchQuery: String): Flow<PagingData<MovieData>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                SearchMoviePagingSource(
                    movieApi = movieApi,
                    searchQuery = searchQuery
                )
            }
        ).flow
    }
}