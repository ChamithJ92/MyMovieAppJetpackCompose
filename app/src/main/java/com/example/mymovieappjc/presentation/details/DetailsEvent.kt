package com.example.mymovieappjc.presentation.details

import com.example.mymovieappjc.presentation.search.SearchEvent

sealed class DetailsEvent{
    data class SelectedMovieId(val movieId: String): DetailsEvent()

   // object CastMovie: DetailsEvent()
}
