package com.example.mymovieappjc.presentation.movie_navigator

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.mymovieappjc.domain.model.MovieData
import com.example.mymovieappjc.domain.model.VideoResponse
import com.example.mymovieappjc.presentation.colors.createGradientBrush
import com.example.mymovieappjc.presentation.components.HomeAppBar
import com.example.mymovieappjc.presentation.details.DetailsViewModel
import com.example.mymovieappjc.presentation.navigation.MovieDrawerMenu
import com.example.mymovieappjc.presentation.home.HomeScreen
import com.example.mymovieappjc.presentation.home.HomeViewModel
import com.example.mymovieappjc.presentation.navigation.DrawerMenuData
import com.example.mymovieappjc.presentation.search.SearchEvent
import com.example.mymovieappjc.presentation.search.SearchState
import com.example.mymovieappjc.presentation.search.SearchViewModel
import com.example.mymovieappjc.presentation.search.SearchWidgetState
import com.example.mymovieappjc.presentation.details.MovieDetailScreen
import com.example.mymovieappjc.presentation.details.MovieVideoScreen
import com.example.mymovieappjc.presentation.drawer_screens.NowPlayingMovies
import com.example.mymovieappjc.presentation.navigation.Route
import com.example.mymovieappjc.presentation.search.SearchScreen
import com.example.mymovieappjc.presentation.drawer_screens.PopularMovies
import com.example.mymovieappjc.presentation.drawer_screens.TrendingMovies
import com.example.mymovieappjc.presentation.drawer_screens.UpcomingMovies
import kotlinx.coroutines.CoroutineScope

@Composable
fun MovieApp() {

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    val topBarState = rememberSaveable { (mutableStateOf(true)) }

    val viewModel: SearchViewModel = hiltViewModel()

    val searchWidgetState by viewModel.searchWidgetState
    val state = viewModel.state.value

    MainScreen(
        drawerState = drawerState,
        navController = navController,
        scrollState = scrollState,
        scope = scope,
        onCloseClicked = {
            viewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
        },
        onSearchTriggered = {
            viewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
        },
        topBarState = topBarState,
        searchTextState = state,
        searchWidgetState = searchWidgetState,
        event = viewModel::onEvent
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    drawerState: DrawerState,
    navController: NavHostController,
    scrollState: ScrollState,
    scope: CoroutineScope,
    onCloseClicked: () -> Unit,
    onSearchTriggered: () -> Unit,
    topBarState: MutableState<Boolean>,
    searchTextState: SearchState,
    searchWidgetState: SearchWidgetState,
    event: (SearchEvent) -> Unit
) {


    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            val gradientColorList = listOf(
                Color(0xCE0281A8),
                Color(0xE8000D5F),
                Color(0xE8000216)
            )
            ModalDrawerSheet(
                modifier = Modifier
                    .width(300.dp)
                    .fillMaxHeight()
                    .background(
                        createGradientBrush(isVertical = true, colors = gradientColorList),
                        RoundedCornerShape(topEnd = 0.dp, bottomEnd = 0.dp)
                    ),
                drawerShape = DrawerDefaults.shape,
                drawerTonalElevation = 0.dp,
                drawerContainerColor = Color.Transparent
            ) {
                val modifier = Modifier
                MovieDrawerMenu(
                    scrollState, navController = navController,
                    scope = scope, scaffoldState = drawerState
                )
            }
        },
        content = {
            val gradientColorList = listOf(
                Color(0xCE0281A8),
                Color(0xE8000D5F),
                Color(0xE8000216)
            )

            Scaffold(
                topBar = {
                    HomeAppBar(
                        drawerState = drawerState,
                        scope = scope,
                        navigateToSearch = {
                            navigateToSearch(
                                navController = navController
                            )
                        },
                        topBarState = topBarState
                    )
                },
                containerColor = Color.Transparent,
                modifier = Modifier.fillMaxSize()
                    .background(
                        createGradientBrush(isVertical = true, colors = gradientColorList)
                    ),
            ) {
                val bottomPadding = it.calculateBottomPadding()

                NavHost(
                    navController = navController,
                    startDestination = DrawerMenuData.HomeScreen.route!!,
                    modifier = Modifier.padding(bottom = bottomPadding)
                ) {
                    composable(route = DrawerMenuData.HomeScreen.route) {
                        val viewModel: HomeViewModel = hiltViewModel()
                        val popularMovieData = viewModel.popularMovies.collectAsLazyPagingItems()
                        val trendingMovieData = viewModel.trendingMovies.collectAsLazyPagingItems()
                        val nowPlayingMovieData = viewModel.nowPlayingMovies.collectAsLazyPagingItems()
                        val upcomingMovieData = viewModel.upcomingMovies.collectAsLazyPagingItems()
                        HomeScreen(
                            popularMovieData = popularMovieData,
                            trendingMovieData = trendingMovieData,
                            nowPlayingMovieData = nowPlayingMovieData,
                            upcomingMovieData = upcomingMovieData,
                            navigateToDetails = { movieData ->
                                navigateToDetails(
                                    navController = navController,
                                    movieData = movieData
                                )
                            },
                            navController = navController
                        )
                        topBarState.value = true
                    }
                    composable(route = DrawerMenuData.TrendingMovies.route!!) {
                        topBarState.value = false
                        val viewModel: HomeViewModel = hiltViewModel()
                        val trendingMovieData = viewModel.trendingMovies.collectAsLazyPagingItems()
                        TrendingMovies(
                            trendingMovieData = trendingMovieData,
                            navigateUp = { navController.navigateUp() },
                            navigateToDetails = { movieData ->
                                navigateToDetails(
                                    navController = navController,
                                    movieData = movieData
                                )
                            }
                        )
                    }

                    composable(route = DrawerMenuData.PopularMovies.route!!) {
                        topBarState.value = false
                        val viewModel: HomeViewModel = hiltViewModel()
                        val popularMovieData = viewModel.popularMovies.collectAsLazyPagingItems()
                        PopularMovies(
                            popularMovies = popularMovieData,
                            navigateUp = { navController.navigateUp() },
                            navigateToDetails = { movieData ->
                                navigateToDetails(
                                    navController = navController,
                                    movieData = movieData
                                )
                            }
                        )
                    }

                    composable(route = DrawerMenuData.NowPlayingMovies.route!!) {
                        topBarState.value = false
                        val viewModel: HomeViewModel = hiltViewModel()
                        val nowPlayingMovieData = viewModel.nowPlayingMovies.collectAsLazyPagingItems()
                        NowPlayingMovies(
                            nowPlayingMovies = nowPlayingMovieData,
                            navigateUp = { navController.navigateUp() },
                            navigateToDetails = { movieData ->
                                navigateToDetails(
                                    navController = navController,
                                    movieData = movieData
                                )
                            }
                        )
                    }

                    composable(route = DrawerMenuData.UpcomingMovies.route!!) {
                        topBarState.value = false
                        val viewModel: HomeViewModel = hiltViewModel()
                        val upcomingMovieData = viewModel.upcomingMovies.collectAsLazyPagingItems()
                        UpcomingMovies(
                            upcomingMovies = upcomingMovieData,
                            navigateUp = { navController.navigateUp() },
                            navigateToDetails = { movieData ->
                                navigateToDetails(
                                    navController = navController,
                                    movieData = movieData
                                )
                            }
                        )
                    }

                    composable(route = Route.MovieDetailsScreen.route) { navBackStackEntry ->
                        topBarState.value = false

                        navController.previousBackStackEntry?.savedStateHandle?.get<MovieData>("movieData")
                            ?.let { movieData ->
                                val viewModel: DetailsViewModel = hiltViewModel()

                                MovieDetailScreen(
                                    movieData = movieData,
                                    navigateUp = { navController.navigateUp() },
                                    viewModel = viewModel,
                                    navigateToVideo = { videoData ->

                                        navigateToVideo(
                                            navController = navController,
                                            videoData = videoData
                                        )
                                    }
                                )

                            }
                    }

                    composable(route = Route.MovieVideoDetailsScreen.route) { navBackStackEntry ->
                        topBarState.value = false
                        navController.previousBackStackEntry?.savedStateHandle?.get<VideoResponse>("videoData")
                            ?.let { videoData ->
                                MovieVideoScreen(
                                    videoData = videoData,
                                    navigateUp = { navController.navigateUp() }
                                )
                            }
                    }

                    composable(route = Route.SearchScreen.route) { navBackStackEntry ->
                        topBarState.value = false

                        SearchScreen(
                            navigateUp = { navController.navigateUp() },
                            scope = scope,
                            onCloseClicked = onCloseClicked,
                            onSearchTriggered = onSearchTriggered,
                            searchTextState = searchTextState,
                            searchWidgetState = searchWidgetState,
                            event = event,
                            navigateToDetails = { movieData ->
                                navigateToDetails(
                                    navController = navController,
                                    movieData = movieData
                                )
                            }
                        )
                    }
                }
            }
        })
}

private fun navigateToDetails(navController: NavController, movieData: MovieData) {
    navController.currentBackStackEntry?.savedStateHandle?.set("movieData", movieData)
    navController.navigate(
        route = Route.MovieDetailsScreen.route
    )
}

private fun navigateToVideo(
    navController: NavController, videoData: VideoResponse
) {
    navController.currentBackStackEntry?.savedStateHandle?.set("videoData", videoData)
    navController.navigate(
        route = Route.MovieVideoDetailsScreen.route
    )
}

private fun navigateToSearch(
    navController: NavController
) {
    navController.navigate(
        route = Route.SearchScreen.route
    )
}


