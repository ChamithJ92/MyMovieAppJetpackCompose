package com.example.mymovieappjc.presentation.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.mymovieappjc.domain.usecases.movies.MovieUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val movieUseCases: MovieUseCases
): ViewModel() {

    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    private val _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    fun updateSearchWidgetState(newValue: SearchWidgetState){
        _searchWidgetState.value = newValue
    }

    fun onEvent(event: SearchEvent){
        when(event) {
            is SearchEvent.UpdateSearchQuery -> {
                _state.value = state.value.copy(searchQuery =  event.searchQuery)
            }

            is SearchEvent.SearchMovie -> {
                searchMovies()
            }
        }
    }
    private fun searchMovies(){
        val movies = movieUseCases.getSearchMovies(
            searchQuery = state.value.searchQuery
        ).cachedIn(viewModelScope)
        _state.value = state.value.copy(movie = movies)
    }

}