package com.example.mymovieappjc.data.remote.dto

import com.example.mymovieappjc.domain.model.MovieData

data class UpcomingMovieResponse(
    val page: Int? = null,
    val results: List<MovieData>? = null,
    val total_pages: Int? = null,
    val total_results: Int? = null
)