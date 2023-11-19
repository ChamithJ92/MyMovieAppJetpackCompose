package com.example.mymovieappjc.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mymovieappjc.R
import com.example.mymovieappjc.domain.model.MovieData
import com.example.mymovieappjc.presentation.Dimens
import com.example.mymovieappjc.presentation.Dimens.CardHeight3
import com.example.mymovieappjc.presentation.Dimens.LargePadding1
import com.example.mymovieappjc.presentation.Dimens.MediumCornerShape1
import com.example.mymovieappjc.presentation.Dimens.MediumPadding1
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MovieCard(movieData: LazyPagingItems<MovieData>,
              onClickDetails: (MovieData) -> Unit) {

    val pagerState = rememberPagerState(initialPage = 0)

    Column(modifier = Modifier.wrapContentHeight()) {
        HorizontalPager(
            count = movieData.itemCount,
            state = pagerState,
            contentPadding = PaddingValues(start = MediumPadding1, end = LargePadding1),
            itemSpacing = 16.dp,
            modifier = Modifier.height(Dimens.CardHeight3)
        ) { page ->
            Card(
                shape = RoundedCornerShape(MediumCornerShape1),
                onClick = {
                    onClickDetails(movieData[page]!!)
                }
            ) {
                AsyncImage(
                    modifier = Modifier
                        .height(CardHeight3),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://image.tmdb.org/t/p/w500" + movieData[page]?.poster_path)
                        .build(),
                    placeholder = painterResource(id = R.drawable.placeholder_image),
                    error = painterResource(id = R.drawable.error_image),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
            }

        }
    }
}