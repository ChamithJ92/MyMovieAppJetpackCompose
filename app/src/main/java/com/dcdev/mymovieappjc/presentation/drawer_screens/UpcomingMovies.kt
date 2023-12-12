package com.dcdev.mymovieappjc.presentation.drawer_screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.dcdev.mymovieappjc.domain.model.MovieData
import com.dcdev.mymovieappjc.presentation.components.MovieList
import com.dcdev.mymovieappjc.presentation.components.NavigateBackTopAppBar

@Composable
fun UpcomingMovies(upcomingMovies: LazyPagingItems<MovieData>,
                   navigateUp: () -> Unit,
                   navigateToDetails: (MovieData) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {

        NavigateBackTopAppBar(onBackPressed = { navigateUp() }, titleName = "Upcoming Movies")

        MovieList(movies = upcomingMovies, onClickDetails = {
            navigateToDetails(it)
        })

    }
}