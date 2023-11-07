package com.example.mymovieappjc.presentation.navigation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MovieDrawerMenu(
    scrollState: ScrollState,
    navController: NavHostController,
    scope: CoroutineScope,
    scaffoldState: DrawerState
) {

    val menuList = listOf(
        DrawerMenuData.Divider,
        DrawerMenuData.HomeScreen,
        DrawerMenuData.TrendingMovies,
        DrawerMenuData.PopularMovies,
        DrawerMenuData.NowPlayingMovies,
        DrawerMenuData.UpcomingMovies
    )

    //val currentRoute = currentRoute(navController)

    Column(modifier = Modifier.verticalScroll(scrollState)) {
        Text(
            text = "Welcome.",
            color = Color.White,
            modifier = Modifier.padding(start = 20.dp, top = 30.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Millions of movies, TV shows and people to discover. Explore now.",
            color = Color.White,
            modifier = Modifier.padding(start = 20.dp, top = 10.dp, end = 20.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        menuList.forEach { item ->
            when {
                item.isDivider -> {
                    Divider(
                        modifier = Modifier.padding(bottom = 20.dp, top = 20.dp),
                        color = Color.White
                    )
                }

                else -> {
                    MovieDrawerItem(
                        item = item, { item.route },
                        navController = navController,
                        scope = scope,
                        scaffoldState = scaffoldState
                    )
                }
            }
        }
    }
}

@Composable
fun MovieDrawerItem(
    item: DrawerMenuData,
    onItemClick: (DrawerMenuData) -> Unit,
    navController: NavHostController,
    scope: CoroutineScope,
    scaffoldState: DrawerState
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(top = 16.dp)
            .clickable {
                Log.e("=====", "$item")
                onItemClick(item)
                navController.navigate(item.route!!) {
                    launchSingleTop = true
                }
                scope.launch {
                    scaffoldState.close()
                }
            }
    ) {
        Image(
            imageVector = item.icon!!,
            contentDescription = item.title!!,
            modifier = Modifier.weight(0.5f),
            colorFilter = ColorFilter.tint(color = Color.White)
        )
        Text(
            text = item.title,
            modifier = Modifier.weight(2.0f),
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}