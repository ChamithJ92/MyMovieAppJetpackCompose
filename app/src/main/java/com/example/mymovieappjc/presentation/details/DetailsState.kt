package com.example.mymovieappjc.presentation.details

import androidx.paging.PagingData
import com.example.mymovieapp.data.model.responses.Cast
import kotlinx.coroutines.flow.Flow

data class DetailsState(
    val movieId: String = "",
  //  val movieCast: Flow<PagingData<Cast>>? = null
)
