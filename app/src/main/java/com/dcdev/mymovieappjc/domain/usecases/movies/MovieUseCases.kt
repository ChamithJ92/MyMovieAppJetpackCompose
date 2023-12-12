package com.dcdev.mymovieappjc.domain.usecases.movies

data class MovieUseCases(
    val getPopularMovies: GetPopularMovies,
    val getSearchMovies: GetSearchMovies,
    val getTrendingMovies: GetTrendingMovies,
    val getNowPlayingMovies: GetNowPlayingMovies,
    val getUpcomingMovies: GetUpcomingMovies,
    val getCastByMovie: GetCastByMovie,
    val getVideoByMovie: GetVideoByMovie
)
