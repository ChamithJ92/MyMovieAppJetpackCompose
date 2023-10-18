package com.example.mymovieappjc.presentation.movie_navigator

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.mymovieappjc.presentation.colors.createGradientBrush
import com.example.mymovieappjc.presentation.components.MainAppBar
import com.example.mymovieappjc.presentation.navigation.MovieDrawerMenu
import com.example.mymovieappjc.presentation.home.HomeScreen
import com.example.mymovieappjc.presentation.home.HomeViewModel
import com.example.mymovieappjc.presentation.navigation.DrawerMenuData
import com.example.mymovieappjc.presentation.search.SearchEvent
import com.example.mymovieappjc.presentation.search.SearchState
import com.example.mymovieappjc.presentation.search.SearchViewModel
import com.example.mymovieappjc.presentation.search.SearchWidgetState
import com.example.mymovieappjc.ui.screens.MovieDetailScreen
import com.example.mymovieappjc.ui.screens.PopularMovieScreen
import kotlinx.coroutines.CoroutineScope

@Composable
fun MovieApp() {

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val navController = rememberNavController()
    val backstackState = navController.currentBackStackEntryAsState().value
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    val topBarState = rememberSaveable { (mutableStateOf(true)) }

    val viewModel: SearchViewModel = hiltViewModel()

    val searchWidgetState by viewModel.searchWidgetState
    //val searchTextState by viewModel.searchTextState
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
                Color(0xCE12A2CE),
                Color(0xC30151A7),
                Color(0xE81350CA),
                Color(0xE8023292)
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
                Color(0xC301448B),
                Color(0xE80C2D70),
                Color(0xE8001066)
            )

            Scaffold(
                topBar = {
                    MainAppBar(
                        drawerState = drawerState,
                        scope = scope,
                        onCloseClicked = onCloseClicked,
                        onSearchTriggered = onSearchTriggered,
                        topBarState = topBarState,
                        searchTextState = searchTextState,
                        searchWidgetState = searchWidgetState,
                        event = event
                    )
                },
                containerColor = Color.Transparent,
                modifier = Modifier
                    .background(
                        createGradientBrush(isVertical = true, colors = gradientColorList)
                    ),
            ) {

                Navigation(
                    navController = navController,
                    scrollState = scrollState,
                    paddingValues = it,
                    topBarState = topBarState
                )
            }
        })
}

@Composable
fun Navigation(
    navController: NavHostController,
    scrollState: ScrollState,
    paddingValues: PaddingValues,
    topBarState: MutableState<Boolean>
) {
    NavHost(
        navController = navController,
        startDestination = DrawerMenuData.Home.route!!,
        modifier = Modifier.padding(paddingValues = paddingValues)
    ) {
        composable(route = DrawerMenuData.Home.route) {
            val viewModel: HomeViewModel = hiltViewModel()
            val movieData = viewModel.popularMovies.collectAsLazyPagingItems()
            HomeScreen(movieData)
            topBarState.value = true
        }
        composable(route = DrawerMenuData.PopularMovies.route!!) {
            topBarState.value = false
            PopularMovieScreen()
        }

        composable(route = "Detail Screen") { navBackStackEntry ->
            topBarState.value = false
            MovieDetailScreen(scrollState = scrollState, navController = navController)
        }
    }
}

//@Composable
//public fun currentRoute(navController: NavHostController): String? {
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    return navBackStackEntry?.arguments?.getString(KEY_ROUTE)
//}
