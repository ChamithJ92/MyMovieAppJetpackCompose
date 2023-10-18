package com.example.mymovieappjc.domain.usecases.movies

import android.util.Log
import androidx.paging.PagingData
import com.example.mymovieappjc.domain.model.MovieData
import com.example.mymovieappjc.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetSearchMovies(
    private val movieRepository: MovieRepository
) {

    operator fun invoke(searchQuery: String):
            Flow<PagingData<MovieData>> {
        return movieRepository.getSearchMovies(searchQuery = searchQuery)
    }
}