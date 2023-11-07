package com.example.mymovieappjc.data.remote.dto

import com.example.mymovieapp.data.model.responses.Cast
import com.example.mymovieapp.data.model.responses.Crew

data class MovieCastResponse(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)
