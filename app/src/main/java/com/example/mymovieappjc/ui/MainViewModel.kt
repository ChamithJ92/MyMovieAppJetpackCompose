package com.example.mymovieappjc.ui

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovieappjc.MainApp
import com.example.mymovieappjc.State.SearchWidgetState
import com.example.mymovieappjc.model.response.PopularMovieResponse
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repository = getApplication<MainApp>().repository

    /**-- Loading and Error handling --**/
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isError = MutableStateFlow(false)
    val isError: StateFlow<Boolean>
        get() = _isError

    val errorHandler = CoroutineExceptionHandler {
            _, error ->
        if(error is Exception){
            _isError.value = true
        }
    }

    /**-- Search Text Function Handling --**/
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
    fun getPopularMovies(){
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO + errorHandler) {
            _popularMovieResponse.value = repository.getPopularMovies(1)
        }
        _isLoading.value = false
    }

}