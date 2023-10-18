package com.example.mymovieappjc.presentation.components


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.example.mymovieappjc.R
import com.example.mymovieappjc.domain.model.MovieData
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.absoluteValue

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun PagerWithEffect(
    movieData: LazyPagingItems<MovieData>
) {

    Log.e("movieData", ""+ movieData)

    val pagerState = rememberPagerState(initialPage = 1)

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            count = movieData.itemCount,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 90.dp),
            modifier = Modifier.height(350.dp)
        ) { page ->

            Card(shape = RoundedCornerShape(10.dp),
                modifier = Modifier.height(350.dp).graphicsLayer {
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
                }) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://image.tmdb.org/t/p/w500" + movieData[page]?.poster_path)
                        .crossfade(true)
                        .scale(Scale.FILL)
                        .build(),
                    placeholder = painterResource(id = R.drawable.placeholder_image),
                    error = painterResource(id = R.drawable.error_image),
                    contentDescription = null
                )
            }

        }
    }

}
