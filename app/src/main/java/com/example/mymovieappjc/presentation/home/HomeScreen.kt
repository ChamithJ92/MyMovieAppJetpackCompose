package com.example.mymovieappjc.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.mymovieappjc.presentation.components.ErrorUI
import com.example.mymovieappjc.presentation.components.LoadingUI
import com.example.mymovieappjc.presentation.components.PagerWithEffect
import com.example.mymovieappjc.domain.model.MovieData

@Composable
fun HomeScreen(
    movieData: LazyPagingItems<MovieData>
) {

    Column(modifier = Modifier.fillMaxSize().padding(top = 80.dp)) {

        PagerWithEffect(movieData)
    }
}