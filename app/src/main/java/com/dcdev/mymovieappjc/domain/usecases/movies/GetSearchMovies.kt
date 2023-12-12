package com.dcdev.mymovieappjc.domain.usecases.movies

import androidx.paging.PagingData
import com.dcdev.mymovieappjc.domain.model.MovieData
import com.dcdev.mymovieappjc.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetSearchMovies(
    private val movieRepository: MovieRepository
) {

    operator fun invoke(searchQuery: String):
            Flow<PagingData<MovieData>> {
        return movieRepository.getSearchMovies(searchQuery = searchQuery)
    }
}