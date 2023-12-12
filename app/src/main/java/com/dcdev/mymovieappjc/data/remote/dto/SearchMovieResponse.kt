package com.dcdev.mymovieappjc.data.remote.dto

import com.dcdev.mymovieappjc.domain.model.MovieData

data class SearchMovieResponse (
    val page: Int? = null,
    val results: List<MovieData>? = null,
    val total_pages: Int? = null,
    val total_results: Int? = null,
    var searchKey: String? = null
)