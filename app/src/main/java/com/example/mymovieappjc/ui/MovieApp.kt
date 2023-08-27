package com.example.mymovieappjc.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mymovieappjc.State.SearchWidgetState
import com.example.mymovieappjc.colors.createGradientBrush
import com.example.mymovieappjc.components.MainAppBar
import com.example.mymovieappjc.components.MovieDrawerMenu
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieApp(mainViewModel: MainViewModel) {

    val searchWidgetState by mainViewModel.searchWidgetState
    val searchTextState by mainViewModel.searchTextState

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    MainScreen(
        drawerState = drawerState,
        navController = navController,
        scrollState = scrollState,
        scope = scope,
        searchWidgetState = searchWidgetState,
        searchTextState = searchTextState,
        onTextChange = { mainViewModel.updateSearchTextState(newValue = it) },
        onCloseClicked = {
            mainViewModel.updateSearchTextState(newValue = "")
            mainViewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
        },
        onSearchClicked = { Log.d("Search Text", it) },
        onSearchTriggered = {
            mainViewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
        },
        mainViewModel = mainViewModel
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    drawerState: DrawerState,
    navController: NavHostController,
    scrollState: ScrollState,
    scope: CoroutineScope,
    searchWidgetState: SearchWidgetState,
    searchTextState: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered: () -> Unit,
    mainViewModel: MainViewModel
) {


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            val gradientColorList = listOf(
                Color(0xCE12A2CE),
                Color(0xC30151A7),
                Color(0xC6012C49),
                Color(0xE80343C4)
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
                MovieDrawerMenu(scrollState)
            }
        },
        content = {
            Scaffold(topBar = {
                MainAppBar(
                    drawerState = drawerState,
                    scope = scope,
                    searchWidgetState = searchWidgetState,
                    searchTextState = searchTextState,
                    onTextChange = onTextChange,
                    onCloseClicked = onCloseClicked,
                    onSearchClicked = onSearchClicked,
                    onSearchTriggered = onSearchTriggered
                )
            }) {

                Navigation(navController)
            }
        })
}

@Composable
fun Navigation(navController: NavHostController) {

}
