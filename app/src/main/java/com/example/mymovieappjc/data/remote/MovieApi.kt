package com.example.mymovieappjc.data.remote

import com.example.mymovieappjc.data.remote.dto.PopularMovieResponse
import com.example.mymovieappjc.data.remote.dto.SearchMovieResponse
import com.example.mymovieappjc.utils.Constance.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("/3/movie/popular")
    suspend fun getPopularMovie(
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = API_KEY
    ) : PopularMovieResponse

    @GET("/3/search/movie")
    suspend fun getSearchMovies(
        @Query("page") page: Int = 1,
        @Query("query") searchKey: String,
        @Query("api_key") apiKey: String = API_KEY
    ): SearchMovieResponse
}