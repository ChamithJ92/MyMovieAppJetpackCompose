package com.example.mymovieappjc.domain.repository

import androidx.paging.PagingData
import com.example.mymovieappjc.domain.model.MovieData
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPopularMovies(): Flow<PagingData<MovieData>>
    fun getSearchMovies(searchQuery: String): Flow<PagingData<MovieData>>
}