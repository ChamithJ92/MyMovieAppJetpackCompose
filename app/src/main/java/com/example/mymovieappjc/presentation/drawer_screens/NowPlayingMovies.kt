package com.example.mymovieappjc.presentation.drawer_screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.example.mymovieappjc.domain.model.MovieData
import com.example.mymovieappjc.domain.usecases.movies.GetNowPlayingMovies
import com.example.mymovieappjc.presentation.components.MovieList
import com.example.mymovieappjc.presentation.components.NavigateBackTopAppBar

@Composable
fun NowPlayingMovies(nowPlayingMovies: LazyPagingItems<MovieData>,
                     navigateUp: () -> Unit,
                     navigateToDetails: (MovieData) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {

        NavigateBackTopAppBar(onBackPressed = { navigateUp() }, titleName = "Now Playing Movies")

        MovieList(movies = nowPlayingMovies, onClickDetails = {
            navigateToDetails(it)
        })

    }
}