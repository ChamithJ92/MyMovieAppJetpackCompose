package com.dcdev.mymovieappjc.presentation.drawer_screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.dcdev.mymovieappjc.domain.model.MovieData
import com.dcdev.mymovieappjc.presentation.components.MovieList
import com.dcdev.mymovieappjc.presentation.components.NavigateBackTopAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TrendingMovies(
    trendingMovieData: LazyPagingItems<MovieData>,
    navigateUp: () -> Unit,
    navigateToDetails: (MovieData) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
    ) {

        NavigateBackTopAppBar(onBackPressed = { navigateUp() }, titleName = "Trending Movies")

        MovieList(movies = trendingMovieData, onClickDetails = {
            navigateToDetails(it)
        })
    }
}