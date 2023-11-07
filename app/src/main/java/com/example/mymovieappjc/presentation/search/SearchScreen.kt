package com.example.mymovieappjc.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.mymovieappjc.domain.model.MovieData
import com.example.mymovieappjc.presentation.components.MainSearchAppBar
import com.example.mymovieappjc.presentation.components.MovieList
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navigateUp: () -> Unit,
    scope: CoroutineScope,
    onCloseClicked: () -> Unit,
    onSearchTriggered: () -> Unit,
    searchTextState: SearchState,
    searchWidgetState: SearchWidgetState,
    event: (SearchEvent) -> Unit,
    navigateToDetails: (MovieData) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {

        MainSearchAppBar(
            onBackPressed = { navigateUp()  },
            scope = scope,
            onCloseClicked = onCloseClicked,
            onSearchTriggered = onSearchTriggered,
            searchTextState = searchTextState,
            searchWidgetState = searchWidgetState,
            event = event
            )

        searchTextState.movie?.let {
            val movies = it.collectAsLazyPagingItems()
            MovieList(movies = movies, onClickDetails = {
                navigateToDetails(it)
            })
        }

    }

}