package com.dcdev.mymovieappjc.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import com.dcdev.mymovieappjc.R
import com.dcdev.mymovieappjc.presentation.components.PagerWithEffect
import com.dcdev.mymovieappjc.domain.model.MovieData
import com.dcdev.mymovieappjc.presentation.Dimens.MediumPadding1
import com.dcdev.mymovieappjc.presentation.Dimens.MediumPadding3
import com.dcdev.mymovieappjc.presentation.Dimens.MediumPadding4
import com.dcdev.mymovieappjc.presentation.Dimens.MediumPadding7
import com.dcdev.mymovieappjc.presentation.components.MovieCard
import com.dcdev.mymovieappjc.presentation.components.SubTitleComponentBar
import com.dcdev.mymovieappjc.presentation.navigation.DrawerMenuData

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
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = MediumPadding7)
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
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
                    modifier = Modifier.padding(top = MediumPadding1, bottom = MediumPadding1),
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
                    modifier = Modifier.padding(top = MediumPadding1, bottom = MediumPadding1),
                    color = colorResource(id = R.color.divider_col_2)
                )

                MovieCard(movieData = nowPlayingMovieData,
                    onClickDetails = { navigateToDetails(it) })

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = MediumPadding3,
                            top = MediumPadding4,
                            end = MediumPadding3
                        ),
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
                    modifier = Modifier.padding(top = MediumPadding1, bottom = MediumPadding1),
                    color = colorResource(id = R.color.divider_col_2)
                )

                MovieCard(movieData = upcomingMovieData,
                    onClickDetails = { navigateToDetails(it) })

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = MediumPadding3,
                            top = MediumPadding4,
                            end = MediumPadding3
                        ),
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
    }

}
