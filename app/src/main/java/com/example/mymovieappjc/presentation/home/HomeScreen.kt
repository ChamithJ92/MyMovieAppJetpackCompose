package com.example.mymovieappjc.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import com.example.mymovieappjc.R
import com.example.mymovieappjc.presentation.components.ErrorUI
import com.example.mymovieappjc.presentation.components.LoadingUI
import com.example.mymovieappjc.presentation.components.PagerWithEffect
import com.example.mymovieappjc.domain.model.MovieData
import com.example.mymovieappjc.presentation.components.MovieCard
import com.example.mymovieappjc.presentation.components.SubTitleComponentBar
import com.example.mymovieappjc.presentation.navigation.DrawerMenuData

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    popularMovieData: LazyPagingItems<MovieData>,
    trendingMovieData: LazyPagingItems<MovieData>,
    nowPlayingMovieData: LazyPagingItems<MovieData>,
    upcomingMovieData: LazyPagingItems<MovieData>,
    navigateToDetails: (MovieData) -> Unit,
    navController: NavHostController,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {

        PagerWithEffect(popularMovieData, onClickDetails = { navigateToDetails(it) })

        SubTitleComponentBar(
            subTitle = "Trending",
            midTitle = "Today",
            onClickSeeAll = {
                navController.navigate(route = DrawerMenuData.TrendingMovies.route!!) {
                    launchSingleTop = true
                }
            })

        Divider(
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
            color = colorResource(id = R.color.divider_col_2)
        )

        MovieCard(movieData = trendingMovieData,
            onClickDetails = { navigateToDetails(it) })

        SubTitleComponentBar(
            subTitle = "Popular",
            midTitle = "In Theaters",
            onClickSeeAll = {
                navController.navigate(route = DrawerMenuData.PopularMovies.route!!) {
                    launchSingleTop = true
                }
            })

        Divider(
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
            color = colorResource(id = R.color.divider_col_2)
        )

        MovieCard(movieData = nowPlayingMovieData,
            onClickDetails = { navigateToDetails(it) })

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, top = 12.dp, end = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Now Playing",
                fontSize = 20.sp,
                color = colorResource(id = R.color.txt_col_1),
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold
            )

            ClickableText(
                text = AnnotatedString("See All"),
                onClick = {
                    navController.navigate(route = DrawerMenuData.NowPlayingMovies.route!!) {
                        launchSingleTop = true
                    }
                },
                style = TextStyle(
                    fontSize = 13.sp,
                    color = colorResource(id = R.color.txt_col_2),
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Medium
                ),
            )
        }

        Divider(
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
            color = colorResource(id = R.color.divider_col_2)
        )

        MovieCard(movieData = upcomingMovieData,
            onClickDetails = { navigateToDetails(it) })

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, top = 12.dp, end = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Upcoming",
                fontSize = 20.sp,
                color = colorResource(id = R.color.txt_col_1),
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold
            )

            ClickableText(
                text = AnnotatedString("See All"),
                onClick = {
                    navController.navigate(route = DrawerMenuData.UpcomingMovies.route!!) {
                        launchSingleTop = true
                    }
                },
                style = TextStyle(
                    fontSize = 13.sp,
                    color = colorResource(id = R.color.txt_col_2),
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Medium
                ),
            )
        }
    }


}
