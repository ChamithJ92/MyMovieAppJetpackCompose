package com.example.mymovieappjc.data

import com.example.mymovieappjc.network.MovieManager

class Repository(val manager: MovieManager) {

    suspend fun getPopularMovies(popularMoviePage: Int) = manager.getPopularMovies(popularMoviePage = popularMoviePage)
}