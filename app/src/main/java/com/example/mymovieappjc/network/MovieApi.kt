package com.example.mymovieappjc.network

import com.example.mymovieappjc.model.response.PopularMovieResponse
import com.example.mymovieappjc.utils.Constance.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("/3/movie/popular")
    suspend fun getPopularMovie(
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = API_KEY
    ) : PopularMovieResponse
}