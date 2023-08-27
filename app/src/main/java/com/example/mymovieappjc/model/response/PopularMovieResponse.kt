package com.example.mymovieappjc.model.response

data class PopularMovieResponse (
    val page: Int? = null,
    val results: List<MovieData>? = null,
    val total_pages: Int? = null,
    val total_results: Int? = null
)