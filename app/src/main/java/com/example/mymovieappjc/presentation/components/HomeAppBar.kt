package com.example.mymovieappjc.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.mymovieappjc.presentation.search.SearchWidgetState
import com.example.mymovieappjc.presentation.search.SearchEvent
import com.example.mymovieappjc.presentation.search.SearchState
import com.example.mymovieappjc.ui.theme.MyMovieAppJCTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(drawerState: DrawerState, scope: CoroutineScope, onSearchClicked: () -> Unit) {

    Box(modifier = Modifier.padding(10.dp)) {
        Card(modifier = Modifier.requiredHeight(50.dp),) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                IconButton(onClick = {
                    scope.launch {
                        drawerState.open()
                    }
                }) {
                    Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.Black)
                }
                Text(text = "Home", modifier = Modifier.weight(2.0f), fontSize = 17.sp, color = Color.Black)
                IconButton(onClick = {
                    onSearchClicked()
                }) {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color.Black
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: () -> Unit
) {
    Box(modifier = Modifier.padding(10.dp)) {
        Card(modifier = Modifier.requiredHeight(50.dp)) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                color = Color.Transparent
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = text,
                    onValueChange = { onTextChange(it) },
                    placeholder = {
                        Text(
                            text = "Search here..",
                            modifier = Modifier.alpha(1.0f),
                            fontSize = 15.sp,
                            color = Color.Black
                        )
                    },
                    textStyle = TextStyle(fontSize = 18.sp),
                    singleLine = true,
                    leadingIcon = {
                        IconButton(modifier = Modifier.alpha(1.0f), onClick = {}) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search Icon",
                                tint = Color.Black
                            )
                        }
                    },
                    trailingIcon = {
                        IconButton(onClick = {
                            if (text.isNotEmpty()) {
                                onTextChange("")
                            } else {
                                onCloseClicked()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close Icon",
                                tint = Color.Black
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            onSearchClicked()
                        }
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        cursorColor = Color.Black.copy(alpha = 1.0f),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppBar(
    drawerState: DrawerState,
    scope: CoroutineScope,
    onCloseClicked: () -> Unit,
    onSearchTriggered: () -> Unit,
    topBarState: MutableState<Boolean>,
    searchTextState: SearchState,
    searchWidgetState: SearchWidgetState,
    event: (SearchEvent) -> Unit
) {
    if (topBarState.value) {
        when (searchWidgetState) {
            SearchWidgetState.CLOSED -> {
                HomeAppBar(
                    drawerState = drawerState,
                    scope = scope,
                    onSearchClicked = onSearchTriggered
                )
            }

            SearchWidgetState.OPENED -> {
                SearchAppBar(
                    text = searchTextState.searchQuery,
                    onTextChange = {event(SearchEvent.UpdateSearchQuery(it))},
                    onCloseClicked = onCloseClicked,
                    onSearchClicked = { event(SearchEvent.SearchMovie) }
                )

                searchTextState.movie?.let {
                    val movies = it.collectAsLazyPagingItems()
                    Log.e("=====", ""+ movies)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyMovieAppJCTheme {
        //MovieApp(viewModel())
    }
}

