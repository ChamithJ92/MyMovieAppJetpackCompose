package com.dcdev.mymovieappjc.presentation.search

sealed class SearchEvent {

    data class UpdateSearchQuery(val searchQuery: String): SearchEvent()

    object SearchMovie: SearchEvent()
}
