package com.example.mymovieappjc.ui

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.example.mymovieappjc.MainApp
import com.example.mymovieappjc.State.SearchWidgetState
import com.example.mymovieappjc.model.response.PopularMovieResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repository = getApplication<MainApp>().repository

    private val _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    private val _searchTextState: MutableState<String> =
        mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState

    fun updateSearchWidgetState(newValue: SearchWidgetState){
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String){
        _searchTextState.value = newValue
    }

    /**-- Get Popular Movies --**/

    private val _popularMovieResponse = MutableStateFlow(PopularMovieResponse())
    val popularMovieResponse: StateFlow<PopularMovieResponse>
        get() = _popularMovieResponse

}