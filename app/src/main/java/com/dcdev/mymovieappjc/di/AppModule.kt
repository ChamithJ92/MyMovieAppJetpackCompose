package com.dcdev.mymovieappjc.di

import com.dcdev.mymovieappjc.data.remote.MovieApi
import com.dcdev.mymovieappjc.data.remote.repository.MovieRepositoryImpl
import com.dcdev.mymovieappjc.domain.repository.MovieRepository
import com.dcdev.mymovieappjc.domain.usecases.movies.GetCastByMovie
import com.dcdev.mymovieappjc.domain.usecases.movies.GetNowPlayingMovies
import com.dcdev.mymovieappjc.domain.usecases.movies.GetPopularMovies
import com.dcdev.mymovieappjc.domain.usecases.movies.GetSearchMovies
import com.dcdev.mymovieappjc.domain.usecases.movies.GetTrendingMovies
import com.dcdev.mymovieappjc.domain.usecases.movies.GetUpcomingMovies
import com.dcdev.mymovieappjc.domain.usecases.movies.GetVideoByMovie
import com.dcdev.mymovieappjc.domain.usecases.movies.MovieUseCases
import com.dcdev.mymovieappjc.utils.Constance.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieApi(): MovieApi {

        val logging = HttpLoggingInterceptor()

         val httpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .apply {
                logging.level = HttpLoggingInterceptor.Level.BODY
                addInterceptor(logging)
                //addNetworkInterceptor(logging)
            }.build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
            .create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        movieApi: MovieApi
    ): MovieRepository = MovieRepositoryImpl(movieApi)

    @Provides
    @Singleton
    fun provideMovieUseCases(
        movieRepository: MovieRepository
    ): MovieUseCases{
        return MovieUseCases(
            getPopularMovies = GetPopularMovies(movieRepository),
            getSearchMovies = GetSearchMovies(movieRepository),
            getTrendingMovies = GetTrendingMovies(movieRepository),
            getNowPlayingMovies = GetNowPlayingMovies(movieRepository),
            getUpcomingMovies = GetUpcomingMovies(movieRepository),
            getCastByMovie = GetCastByMovie(movieRepository),
            getVideoByMovie = GetVideoByMovie(movieRepository)
        )
    }
}