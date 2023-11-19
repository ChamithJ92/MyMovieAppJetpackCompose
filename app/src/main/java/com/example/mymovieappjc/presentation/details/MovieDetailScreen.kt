package com.example.mymovieappjc.presentation.details

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mymovieapp.data.model.responses.Cast
import com.example.mymovieappjc.R
import com.example.mymovieappjc.domain.model.MovieData
import com.example.mymovieappjc.domain.model.VideoResponse
import com.example.mymovieappjc.presentation.components.CustomerCircularProgressIndicator
import com.example.mymovieappjc.presentation.components.NavigateBackTopAppBar
import com.example.mymovieappjc.utils.ResourceState
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MovieDetailScreen(
    movieData: MovieData,
    navigateUp: () -> Unit,
    viewModel: DetailsViewModel,
    navigateToVideo: (VideoResponse) -> Unit
) {

    val context = LocalContext.current

    viewModel.getCast(movieId = movieData.id.toString())
    val castData by viewModel.castData.collectAsState()

    viewModel.getVideo(movieId = movieData.id.toString())
    val videoData by viewModel.videoData.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {

        NavigateBackTopAppBar(onBackPressed = { navigateUp() }, titleName = "Movie Details")

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 10.dp,
                end = 10.dp,
                top = 10.dp
            )
        ) {
            item {
                Box(
                    contentAlignment = Alignment.BottomStart
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(context = context)
                            .data("https://image.tmdb.org/t/p/w500" + movieData.backdrop_path)
                            .build(),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .clip(MaterialTheme.shapes.medium),
                        contentScale = ContentScale.Crop
                    )

                    val pStatus: Double = movieData.vote_average!! * 10
                    CustomerCircularProgressIndicator(
                        modifier = Modifier
                            .size(100.dp)
                            .background(Color.Transparent),
                        initialValue = pStatus.toInt(),
                        primaryColor = colorResource(id = R.color.progress_col_one),
                        secondaryColor = colorResource(id = R.color.progress_col_two),
                        circleRadius = 80f,
                        onPositionChange = {}
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = movieData.title ?: movieData.original_name!!,
                    style = MaterialTheme.typography.displaySmall,
                    color = colorResource(
                        id = R.color.txt_col_1
                    ),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 22.sp,
                    fontFamily = FontFamily(
                        Font(R.font.poppins_bold)
                    )
                )

                Row(
                    modifier = Modifier.padding(top = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Image(
                        imageVector = Icons.Outlined.CalendarMonth,
                        contentDescription = "",
                        modifier = Modifier.padding(end = 5.dp),
                        colorFilter = ColorFilter.tint(color = Color.White)
                    )

                    Text(
                        text = "Release Date: ",
                        style = MaterialTheme.typography.displaySmall,
                        color = colorResource(
                            id = R.color.txt_col_1
                        ),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        fontFamily = FontFamily(
                            Font(R.font.poppins_bold)
                        )
                    )

                    Text(
                        modifier = Modifier.padding(start = 5.dp),
                        text = movieData.first_air_date ?: movieData.release_date!!,
                        style = MaterialTheme.typography.displaySmall,
                        color = colorResource(
                            id = R.color.txt_col_1
                        ),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        fontFamily = FontFamily(
                            Font(R.font.poppins_medium)
                        )
                    )
                }

                IconButton(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp)
                    .height(30.dp)
                    .background(
                        color = colorResource(id = R.color.btn_col),
                        shape = RoundedCornerShape(5.dp)
                    ),
                    onClick = {
                        when (videoData) {
                            is ResourceState.Loading -> {
                                Log.d("Details Video", "+++++")
                            }

                            is ResourceState.Success -> {

                                val videoResponse = (videoData as ResourceState.Success).data

                                if (videoResponse.results.size != 0) {
                                    navigateToVideo(videoResponse.results[0])
                                } else {
                                    // show snacbar
                                    Toast.makeText(context, "No video response data!", Toast.LENGTH_SHORT).show()
                                }
                            }

                            is ResourceState.Error -> {
                                //
                            }
                        }
                    }) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "",
                            tint = Color.White
                        )

                        Text(
                            modifier = Modifier.padding(start = 5.dp),
                            text = "Play", fontSize = 13.sp,
                            color = colorResource(id = R.color.txt_col_1)
                        )
                    }
                }

                Divider(
                    modifier = Modifier.padding(top = 20.dp, bottom = 10.dp),
                    color = colorResource(id = R.color.divider_col_2)
                )

                Text(
                    text = "Overview",
                    style = MaterialTheme.typography.displaySmall,
                    color = colorResource(
                        id = R.color.txt_col_1
                    ),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(
                        Font(R.font.poppins_bold)
                    )
                )

                Text(
                    text = movieData.overview!!,
                    style = MaterialTheme.typography.displaySmall,
                    color = colorResource(
                        id = R.color.txt_col_1
                    ),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    lineHeight = 20.sp,
                    fontFamily = FontFamily(
                        Font(R.font.poppins_medium)
                    )
                )

                Divider(
                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                    color = colorResource(id = R.color.divider_col_2)
                )

                Text(
                    text = "Series Cast",
                    style = MaterialTheme.typography.displaySmall,
                    color = colorResource(
                        id = R.color.txt_col_1
                    ),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(
                        Font(R.font.poppins_bold)
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                when (castData) {
                    is ResourceState.Loading -> {
                        Log.d("Details", "+++++")
                    }

                    is ResourceState.Success -> {

                        val castResponse = (castData as ResourceState.Success).data
                        CastList(castResponse.cast)

                    }

                    is ResourceState.Error -> {
                        //
                    }
                }

            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CastList(cast: List<Cast>) {

    val pagerState = rememberPagerState(initialPage = 0)

    Column(
        modifier = Modifier
            .wrapContentHeight()
            .padding(bottom = 10.dp)
    ) {
        HorizontalPager(
            count = cast.size,
            state = pagerState,
            contentPadding = PaddingValues(start = 0.dp, end = 220.dp),
            modifier = Modifier.height(210.dp)
        ) { page ->
            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .height(210.dp)
            ) {
                Box(
                    contentAlignment = Alignment.BottomStart
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https://image.tmdb.org/t/p/w500" + cast[page].profile_path)
                            .build(),
                        placeholder = painterResource(id = R.drawable.placeholder_image),
                        error = painterResource(id = R.drawable.error_image),
                        contentDescription = null
                    )
                    Text(
                        modifier = Modifier.padding(start = 5.dp, end = 5.dp, bottom = 5.dp),
                        fontSize = 13.sp,
                        text = cast[page].original_name ?: cast[page].name,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.white)
                    )

                }
            }

        }
    }

}
