package com.example.mymovieappjc.domain.usecases.movies

import androidx.paging.PagingData
import com.example.mymovieapp.data.model.responses.Cast
import com.example.mymovieappjc.data.remote.dto.MovieCastResponse
import com.example.mymovieappjc.domain.model.MovieData
import com.example.mymovieappjc.domain.repository.MovieRepository
import com.example.mymovieappjc.utils.ResourceState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class GetCastByMovie(
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(movieId: String): Flow<ResourceState<MovieCastResponse>>{
        return movieRepository.getMovieCast(movieId = movieId)
    }
}