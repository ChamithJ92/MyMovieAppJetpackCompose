package com.example.mymovieappjc.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.mymovieappjc.domain.usecases.movies.MovieUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieUseCases: MovieUseCases
): ViewModel() {

    val popularMovies = movieUseCases.getPopularMovies().cachedIn(viewModelScope)

    val trendingMovies = movieUseCases.getTrendingMovies().cachedIn(viewModelScope)

    val nowPlayingMovies = movieUseCases.getNowPlayingMovies().cachedIn(viewModelScope)

    val upcomingMovies = movieUseCases.getUpcomingMovies().cachedIn(viewModelScope)

}