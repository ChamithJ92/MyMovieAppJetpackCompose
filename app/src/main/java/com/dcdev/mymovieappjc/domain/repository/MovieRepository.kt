package com.dcdev.mymovieappjc.domain.repository

import androidx.paging.PagingData
import com.dcdev.mymovieappjc.data.remote.dto.MovieCastResponse
import com.dcdev.mymovieappjc.data.remote.dto.MovieVideosResponse
import com.dcdev.mymovieappjc.domain.model.MovieData
import com.dcdev.mymovieappjc.utils.ResourceState
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPopularMovies(): Flow<PagingData<MovieData>>
    fun getSearchMovies(searchQuery: String): Flow<PagingData<MovieData>>

    fun getTrendingMovies(): Flow<PagingData<MovieData>>

    fun getNowPlayingMovies(): Flow<PagingData<MovieData>>

    fun getUpcomingMovies(): Flow<PagingData<MovieData>>

    suspend fun getMovieCast(movieId: String): Flow<ResourceState<MovieCastResponse>>

    suspend fun getMovieVideo(movieId: String): Flow<ResourceState<MovieVideosResponse>>
}