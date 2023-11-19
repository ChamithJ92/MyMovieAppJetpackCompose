package com.example.mymovieappjc.presentation.drawer_screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.mymovieappjc.domain.model.MovieData
import com.example.mymovieappjc.presentation.components.MovieList
import com.example.mymovieappjc.presentation.components.NavigateBackTopAppBar

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