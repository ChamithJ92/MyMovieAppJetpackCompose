package com.dcdev.mymovieappjc.presentation.search

import androidx.paging.PagingData
import com.dcdev.mymovieappjc.domain.model.MovieData
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val movie: Flow<PagingData<MovieData>>? = null
)
