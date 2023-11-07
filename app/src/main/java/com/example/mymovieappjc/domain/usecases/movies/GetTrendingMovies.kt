package com.example.mymovieappjc.domain.usecases.movies

import androidx.paging.PagingData
import com.example.mymovieappjc.domain.model.MovieData
import com.example.mymovieappjc.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetTrendingMovies(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(): Flow<PagingData<MovieData>>{
        return movieRepository.getTrendingMovies()
    }
}