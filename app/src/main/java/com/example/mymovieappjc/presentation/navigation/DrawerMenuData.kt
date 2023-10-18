package com.example.mymovieappjc.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PlayCircle
import androidx.compose.material.icons.outlined.Stars
import androidx.compose.material.icons.outlined.TrendingUp
import androidx.compose.material.icons.outlined.Upcoming
import androidx.compose.ui.graphics.vector.ImageVector

sealed class DrawerMenuData(
    val route: String? = null,
    val icon: ImageVector? = null, val title: String? = null,
    val isDivider: Boolean = false
){
    object Home: DrawerMenuData(
        route = "home",
        icon = Icons.Outlined.Home,
        title = "Home"
    )
    object TrendingMovies: DrawerMenuData(
        route = "home",
        icon = Icons.Outlined.TrendingUp,
        title = "Trending Movies"
    )
    object PopularMovies: DrawerMenuData(
        route = "popular_movies",
        icon = Icons.Outlined.Stars,
        title = "Popular Movies"
    )
    object NowPlayingMovies: DrawerMenuData(
        route = "now_playing_movies",
        icon = Icons.Outlined.PlayCircle,
        title = "Now Playing Movies"
    )
    object UpcomingMovies: DrawerMenuData(
        route = "upcoming_movies",
        icon = Icons.Outlined.Upcoming,
        title = "Upcoming Movies"
    )
    object Divider: DrawerMenuData(
        isDivider = true
    )
}