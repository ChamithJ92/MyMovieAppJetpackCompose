package com.dcdev.mymovieappjc.presentation.components


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.util.lerp
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dcdev.mymovieappjc.R
import com.dcdev.mymovieappjc.domain.model.MovieData
import com.dcdev.mymovieappjc.presentation.Dimens.CardHeight2
import com.dcdev.mymovieappjc.presentation.Dimens.MediumCornerShape1
import com.dcdev.mymovieappjc.presentation.Dimens.MediumPadding5
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.absoluteValue

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PagerWithEffect(
    movieData: LazyPagingItems<MovieData>,
    onClickDetails: (MovieData) -> Unit
) {
    val pagerState = rememberPagerState(initialPage = 1)

    Column(modifier = Modifier.wrapContentHeight()) {
        HorizontalPager(
            count = movieData.itemCount,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = MediumPadding5),
            modifier = Modifier.height(CardHeight2)
        ) { page ->

            Card(shape = RoundedCornerShape(MediumCornerShape1),
                modifier = Modifier.height(CardHeight2).graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                    lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }
                    /**--  set opacity other images --**/
                    alpha =
                        lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                },
                onClick = {
                    onClickDetails(movieData[page]!!)
                }) {
                AsyncImage(
                    modifier = Modifier
                        .height(CardHeight2),
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
