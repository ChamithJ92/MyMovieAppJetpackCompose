package com.dcdev.mymovieappjc.data.remote.dto

import com.dcdev.mymovieapp.data.model.responses.Cast
import com.dcdev.mymovieapp.data.model.responses.Crew

data class MovieCastResponse(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)
