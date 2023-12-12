package com.dcdev.mymovieappjc.presentation.details

sealed class DetailsEvent{
    data class SelectedMovieId(val movieId: String): DetailsEvent()

   // object CastMovie: DetailsEvent()
}
