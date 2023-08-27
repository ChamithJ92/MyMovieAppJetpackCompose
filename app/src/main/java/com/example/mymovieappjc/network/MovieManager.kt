package com.example.mymovieappjc.network

import com.example.mymovieappjc.model.response.PopularMovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieManager(private val service: MovieApi) {

    suspend fun getPopularMovies(popularMoviePage: Int): PopularMovieResponse = withContext(Dispatchers.IO){
        service.getPopularMovie(page = popularMoviePage)
    }
}