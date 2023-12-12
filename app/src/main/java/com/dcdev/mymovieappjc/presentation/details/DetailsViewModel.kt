package com.dcdev.mymovieappjc.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dcdev.mymovieappjc.data.remote.dto.MovieCastResponse
import com.dcdev.mymovieappjc.data.remote.dto.MovieVideosResponse
import com.dcdev.mymovieappjc.domain.usecases.movies.MovieUseCases
import com.dcdev.mymovieappjc.utils.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val movieUseCases: MovieUseCases
): ViewModel()  {

    // Gte Cast By Movie Id
    private val _cast: MutableStateFlow<ResourceState<MovieCastResponse>> =
        MutableStateFlow(ResourceState.Loading())

    val castData: StateFlow<ResourceState<MovieCastResponse>> = _cast
    fun getCast(movieId: String){
        viewModelScope.launch(Dispatchers.IO) {
            movieUseCases.getCastByMovie(movieId = movieId)
                .collectLatest { castResponse ->
                    _cast.value = castResponse

            }
        }
    }

    // Get Video By Movie Id

    private val _video: MutableStateFlow<ResourceState<MovieVideosResponse>> =
        MutableStateFlow(ResourceState.Loading())

    val videoData: StateFlow<ResourceState<MovieVideosResponse>> = _video
    fun getVideo(movieId: String){
        viewModelScope.launch(Dispatchers.IO) {
            movieUseCases.getVideoByMovie(movieId = movieId)
                .collectLatest { videoResponse ->
                    _video.value = videoResponse

                }
        }
    }

}