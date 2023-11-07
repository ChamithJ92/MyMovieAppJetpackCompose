package com.example.mymovieappjc.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.mymovieapp.data.model.responses.Cast
import com.example.mymovieappjc.data.remote.MovieApi
import com.example.mymovieappjc.data.remote.MoviePagingSource
import com.example.mymovieappjc.data.remote.NowPlayingMoviesPagingSource
import com.example.mymovieappjc.data.remote.SearchMoviePagingSource
import com.example.mymovieappjc.data.remote.TrendingMoviePagingSource
import com.example.mymovieappjc.data.remote.UpcomingMoviePagingSource
import com.example.mymovieappjc.data.remote.dto.MovieCastResponse
import com.example.mymovieappjc.data.remote.dto.MovieVideosResponse
import com.example.mymovieappjc.domain.model.MovieData
import com.example.mymovieappjc.domain.repository.MovieRepository
import com.example.mymovieappjc.utils.Constance.Companion.API_KEY
import com.example.mymovieappjc.utils.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class MovieRepositoryImpl(
    private val movieApi: MovieApi
): MovieRepository {
    override fun getPopularMovies(): Flow<PagingData<MovieData>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                MoviePagingSource(
                    movieApi = movieApi
                )
            }
        ).flow
    }

    override fun getTrendingMovies(): Flow<PagingData<MovieData>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                TrendingMoviePagingSource(movieApi = movieApi)
            }
        ).flow
    }

    override fun getNowPlayingMovies(): Flow<PagingData<MovieData>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                NowPlayingMoviesPagingSource(movieApi = movieApi)
            }
        ).flow
    }

    override fun getUpcomingMovies(): Flow<PagingData<MovieData>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                UpcomingMoviePagingSource(movieApi = movieApi)
            }
        ).flow
    }

    override fun getSearchMovies(searchQuery: String): Flow<PagingData<MovieData>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                SearchMoviePagingSource(
                    movieApi = movieApi,
                    searchQuery = searchQuery
                )
            }
        ).flow
    }
    override suspend fun getMovieCast(movieId: String): Flow<ResourceState<MovieCastResponse>> {
        return flow {
            emit(ResourceState.Loading())

            val response = movieApi.getMovieCastDetails("/3/movie/${movieId}/credits?api_key=${API_KEY}")

            if(response.isSuccessful && response.body() != null){
                emit(ResourceState.Success(response.body()!!))
            }else{
                emit(ResourceState.Error("Error Fetching Data"))
            }
        }.catch { e ->
            emit(ResourceState.Error(e?.localizedMessage ?: "some error in flow"))
        }
    }

    override suspend fun getMovieVideo(movieId: String): Flow<ResourceState<MovieVideosResponse>> {
        return flow {
            emit(ResourceState.Loading())

            val response = movieApi.getMovieVideoDetails("/3/movie/${movieId}/videos?api_key=${API_KEY}")

            if(response.isSuccessful && response.body() != null){
                emit(ResourceState.Success(response.body()!!))
            }else{
                emit(ResourceState.Error("Error Fetching Data"))
            }
        }.catch { e ->
            emit(ResourceState.Error(e?.localizedMessage ?: "some error in flow"))
        }
    }
}