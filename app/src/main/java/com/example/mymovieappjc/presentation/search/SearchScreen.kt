package com.example.mymovieappjc.presentation.search

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.mymovieappjc.R
import com.example.mymovieappjc.domain.model.MovieData
import com.example.mymovieappjc.presentation.components.MainSearchAppBar
import com.example.mymovieappjc.presentation.components.MovieList
import kotlinx.coroutines.CoroutineScope

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
            onBackPressed = { navigateUp() },
            scope = scope,
            onCloseClicked = onCloseClicked,
            onSearchTriggered = onSearchTriggered,
            searchTextState = searchTextState,
            searchWidgetState = searchWidgetState,
            event = event
        )

        if (searchTextState.movie != null) {
            searchTextState.movie.let {
                val movies = it.collectAsLazyPagingItems()

                if (movies.itemCount != 0) {
                    MovieList(movies = movies, onClickDetails = {
                        navigateToDetails(it)
                    })
                } else {
                    EmptySearch("No Search Results.")
                }
            }
        } else {
            EmptySearch("Search Movie..")
        }
    }

}

@Composable
fun EmptySearch(message: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Icon(
            painter = painterResource(id = R.drawable.ic_search_document),
            contentDescription = null,
            tint = if (isSystemInDarkTheme()) Color.White else Color.White,
            modifier = Modifier
                .size(120.dp)
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            textAlign = TextAlign.Center,
            text = message,
            fontSize = 15.sp,
            color = colorResource(id = R.color.txt_col_1),
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold
        )
    }
}