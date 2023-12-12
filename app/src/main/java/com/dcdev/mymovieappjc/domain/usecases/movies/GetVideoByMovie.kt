package com.dcdev.mymovieappjc.domain.usecases.movies

import com.dcdev.mymovieappjc.data.remote.dto.MovieVideosResponse
import com.dcdev.mymovieappjc.domain.repository.MovieRepository
import com.dcdev.mymovieappjc.utils.ResourceState
import kotlinx.coroutines.flow.Flow

class GetVideoByMovie(
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(movieId: String): Flow<ResourceState<MovieVideosResponse>>{
        return movieRepository.getMovieVideo(movieId = movieId)
    }
}