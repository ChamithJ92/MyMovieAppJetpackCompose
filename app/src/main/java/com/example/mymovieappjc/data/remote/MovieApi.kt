package com.example.mymovieappjc.data.remote

import com.example.mymovieappjc.data.remote.dto.MovieCastResponse
import com.example.mymovieappjc.data.remote.dto.MovieVideosResponse
import com.example.mymovieappjc.data.remote.dto.NowPlayingMovieResponse
import com.example.mymovieappjc.data.remote.dto.PopularMovieResponse
import com.example.mymovieappjc.data.remote.dto.SearchMovieResponse
import com.example.mymovieappjc.data.remote.dto.TrendingMovieResponse
import com.example.mymovieappjc.data.remote.dto.UpcomingMovieResponse
import com.example.mymovieappjc.utils.Constance.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface MovieApi {

    @GET("/3/movie/popular")
    suspend fun getPopularMovie(
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = API_KEY
    ) : PopularMovieResponse

    @GET("/3/trending/all/day")
    suspend fun getTrendingMovies(
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = API_KEY
    ): TrendingMovieResponse

    @GET("/3/movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = API_KEY
    ): NowPlayingMovieResponse

    @GET("/3/movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = API_KEY
    ): UpcomingMovieResponse

    @GET("/3/search/movie")
    suspend fun getSearchMovies(
        @Query("page") page: Int = 1,
        @Query("query") searchKey: String,
        @Query("api_key") apiKey: String = API_KEY
    ): SearchMovieResponse

    @GET
    suspend fun getMovieVideoDetails(
        @Url url: String
    ): Response<MovieVideosResponse>

    @GET
    suspend fun getMovieCastDetails(
        @Url url: String
    ): Response<MovieCastResponse>

}