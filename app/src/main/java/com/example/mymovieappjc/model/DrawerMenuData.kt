package com.example.mymovieappjc.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PlayCircle
import androidx.compose.material.icons.outlined.Stars
import androidx.compose.material.icons.outlined.TrendingUp
import androidx.compose.material.icons.outlined.Upcoming
import androidx.compose.ui.graphics.vector.ImageVector

sealed class DrawerMenuData(
    val icon: ImageVector? = null, val title: String? = null,
    val isDivider: Boolean = false
){
    object Home: DrawerMenuData(
        icon = Icons.Outlined.Home,
        title = "Home"
    )
    object TrendingMovies: DrawerMenuData(
        icon = Icons.Outlined.TrendingUp,
        title = "Trending Movies"
    )
    object PopularMovies: DrawerMenuData(
        icon = Icons.Outlined.Stars,
        title = "Popular Movies"
    )
    object NowPlayingMovies: DrawerMenuData(
        icon = Icons.Outlined.PlayCircle,
        title = "Now Playing Movies"
    )
    object UpcomingMovies: DrawerMenuData(
        icon = Icons.Outlined.Upcoming,
        title = "Upcoming Movies"
    )
    object Divider: DrawerMenuData(
        isDivider = true
    )
}
