package com.example.mymovieappjc.presentation.search

import androidx.paging.PagingData
import com.example.mymovieappjc.domain.model.MovieData
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val movie: Flow<PagingData<MovieData>>? = null
)
