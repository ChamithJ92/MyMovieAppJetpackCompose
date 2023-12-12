package com.dcdev.mymovieappjc.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.dcdev.mymovieappjc.presentation.Dimens.CardHeight1
import com.dcdev.mymovieappjc.presentation.Dimens.MediumPadding1
import com.dcdev.mymovieappjc.presentation.Dimens.MediumPadding2
import com.dcdev.mymovieappjc.ui.theme.MyMovieAppJCTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeAppBar(
    drawerState: DrawerState, scope: CoroutineScope,
    navigateToSearch: () -> Unit,
    topBarState: MutableState<Boolean>,
) {

    if (topBarState.value) {
        Box(
            modifier = Modifier.padding(
                MediumPadding1
            ).statusBarsPadding()
        ) {
            Card(modifier = Modifier.requiredHeight(CardHeight1)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(MediumPadding2)
                ) {
                    IconButton(onClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.Black)
                    }
                    Text(
                        text = "Home",
                        modifier = Modifier.weight(2.0f),
                        fontSize = 17.sp,
                        color = Color.Black
                    )
                    IconButton(onClick = {
                        navigateToSearch()
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
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyMovieAppJCTheme {
        //MovieApp(viewModel())
    }
}

