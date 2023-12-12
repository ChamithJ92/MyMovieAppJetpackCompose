package com.dcdev.mymovieappjc.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dcdev.mymovieappjc.R
import com.dcdev.mymovieappjc.domain.model.MovieData
import com.dcdev.mymovieappjc.presentation.Dimens.MediumArrangement1
import com.dcdev.mymovieappjc.presentation.Dimens.MediumCornerShape1
import com.dcdev.mymovieappjc.presentation.Dimens.MediumPadding1
import com.dcdev.mymovieappjc.presentation.Dimens.MediumPadding2
import com.dcdev.mymovieappjc.presentation.Dimens.MediumPadding6
import com.dcdev.mymovieappjc.presentation.Dimens.SmallPadding1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieList(
    modifier: Modifier = Modifier,
    movies: LazyPagingItems<MovieData>,
    onClickDetails: (MovieData) -> Unit
) {
    val handlePagingResult = handlePagingResult(movies = movies)
    if(handlePagingResult) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                start = MediumPadding1,
                top = SmallPadding1,
                end = MediumPadding1
            ),
            content = {
                items(movies.itemCount) { item ->
                    Card(
                        shape = RoundedCornerShape(MediumCornerShape1),
                        modifier = Modifier.padding(MediumPadding2),
                        onClick = {
                            onClickDetails(movies[item]!!)
                        }
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data("https://image.tmdb.org/t/p/w500" + movies[item]?.poster_path)
                                .build(),
                            placeholder = painterResource(id = R.drawable.placeholder_image),
                            error = painterResource(id = R.drawable.error_image),
                            contentDescription = null,
                            contentScale = ContentScale.Fit
                        )
                    }
                }
            })
    }
}

@Composable
fun handlePagingResult(movies: LazyPagingItems<MovieData>): Boolean {

    val loadState = movies.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen()
            false
        }

        else -> {
            true
        }
    }
}

@Composable
fun ShimmerEffect() {
    Column {
        Column(verticalArrangement = Arrangement.spacedBy(MediumArrangement1)) {
            repeat(10){
                MovieCardShimmerEffect(modifier = Modifier.padding(horizontal = MediumPadding6))
            }
        }
    }
}