package com.dcdev.mymovieappjc.data.remote.dto

import com.dcdev.mymovieappjc.domain.model.VideoResponse

data class MovieVideosResponse(
    val id: Int,
    val results: MutableList<VideoResponse>
)
