package com.example.mymovieappjc.domain.repository

import android.content.res.Resources
import androidx.paging.PagingData
import com.example.mymovieapp.data.model.responses.Cast
import com.example.mymovieappjc.data.remote.dto.MovieCastResponse
import com.example.mymovieappjc.data.remote.dto.MovieVideosResponse
import com.example.mymovieappjc.domain.model.MovieData
import com.example.mymovieappjc.utils.ResourceState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MovieRepository {

    fun getPopularMovies(): Flow<PagingData<MovieData>>
    fun getSearchMovies(searchQuery: String): Flow<PagingData<MovieData>>

    fun getTrendingMovies(): Flow<PagingData<MovieData>>

    fun getNowPlayingMovies(): Flow<PagingData<MovieData>>

    fun getUpcomingMovies(): Flow<PagingData<MovieData>>

    suspend fun getMovieCast(movieId: String): Flow<ResourceState<MovieCastResponse>>

    suspend fun getMovieVideo(movieId: String): Flow<ResourceState<MovieVideosResponse>>
}