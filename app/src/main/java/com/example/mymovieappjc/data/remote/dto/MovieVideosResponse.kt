package com.example.mymovieappjc.data.remote.dto

import com.example.mymovieappjc.domain.model.VideoResponse

data class MovieVideosResponse(
    val id: Int,
    val results: MutableList<VideoResponse>
)
