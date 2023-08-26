package com.example.mymovieappjc.components

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mymovieappjc.colors.createGradientBrush
import com.example.mymovieappjc.model.DrawerMenuData

@Composable
fun MovieDrawerMenu(scrollState: ScrollState) {

    val menuList = listOf(
        DrawerMenuData.Divider,
        DrawerMenuData.Home,
        DrawerMenuData.TrendingMovies,
        DrawerMenuData.PopularMovies,
        DrawerMenuData.NowPlayingMovies,
        DrawerMenuData.UpcomingMovies
    )
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
                    MovieDrawerItem(item = item)
                }
            }
        }
    }
}

@Composable
fun MovieDrawerItem(item: DrawerMenuData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(top = 16.dp)
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