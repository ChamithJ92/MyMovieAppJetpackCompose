package com.example.mymovieappjc.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.example.mymovieappjc.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PagerWithEffect() {

    val images = remember {
        mutableStateListOf(
            "https://image.tmdb.org/t/p/w500/vB8o2p4ETnrfiWEgVxHmHWP9yRl.jpg",
            "https://image.tmdb.org/t/p/w500/8Vt6mWEReuy4Of61Lnj5Xj704m8.jpg",
            "https://image.tmdb.org/t/p/w500/8riWcADI1ekEiBguVB9vkilhiQm.jpg",
            "https://image.tmdb.org/t/p/w500/4m1Au3YkjqsxF8iwQy0fPYSxE0h.jpg",
            "https://image.tmdb.org/t/p/w500/gPbM0MK8CP8A174rmUwGsADNYKD.jpg"
        )
    }
    val pagerState = rememberPagerState(initialPage = 2)

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            count = images.size,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 75.dp),
            modifier = Modifier.height(350.dp)
        ) { page ->
            Card(shape = RoundedCornerShape(10.dp),
            modifier = Modifier.graphicsLayer {
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
                        .data(images[page])
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

    // [START android_compose_layouts_pager_transformation]

//    HorizontalPager(state = pagerState) { page ->
//        Card(
//            Modifier
//                .size(200.dp)
//                .graphicsLayer {
//                    // Calculate the absolute offset for the current page from the
//                    // scroll position. We use the absolute value which allows us to mirror
//                    // any effects for both directions
//                    val pageOffset = (
//                            (pagerState.currentPage - page) + pagerState
//                                .currentPageOffsetFraction
//                            ).absoluteValue
//
//                    lerp(
//                        start = 0.85f,
//                        stop = 1f,
//                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
//                    ).also { scale ->
//                        scaleX = scale
//                        scaleY = scale
//                    }
//
//
//                    // We animate the alpha, between 50% and 100%
//                    alpha =
//                        lerp(
//                            start = 0.5f,
//                            stop = 1f,
//                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
//                        )
//                }
//        ) {
//            CoilImage(
//                imageModel = { images[page] },
//                imageOptions = ImageOptions(
//                    contentScale = ContentScale.None,
//                    alignment = Alignment.Center
//                ),
//                //failure = { ImageBitmap.imageResource(id = R.drawable.breaking_news) },
//                loading = {
//                    Box(modifier = Modifier.matchParentSize()) {
//                        CircularProgressIndicator(
//                            modifier = Modifier.align(Alignment.Center)
//                        )
//                    }
//                }
//            )
//            AsyncImage(
//                model = ImageRequest.Builder(LocalContext.current)
//                    .data("https://example.com/image.jpg")
//                    .crossfade(true)
//                    .build(),
//                placeholder = painterResource(R.drawable.placeholder),
//                contentDescription = stringResource(R.string.description),
//                contentScale = ContentScale.Crop,
//                modifier = Modifier.clip(CircleShape)
//            )
//        }
//    }
    // [END android_compose_layouts_pager_transformation]
}
