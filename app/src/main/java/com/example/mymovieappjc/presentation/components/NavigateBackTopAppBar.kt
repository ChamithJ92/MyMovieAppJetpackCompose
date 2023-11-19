package com.example.mymovieappjc.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mymovieappjc.R
import com.example.mymovieappjc.presentation.colors.createGradientBrush

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigateBackTopAppBar(onBackPressed: () -> Unit = {}, titleName: String) {

    val gradientColorList = listOf(
        Color(0xCE0281A8),
        Color(0xE8000D5F),
        Color(0xE8000216)
    )
    TopAppBar(modifier = Modifier
        .background(
            createGradientBrush(isVertical = true, colors = gradientColorList)
        ),
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent),
        title = {
            Text(
                text = titleName,
                textAlign = TextAlign.Center,
                color = colorResource(id = R.color.white),
                fontSize = 20.sp,
                fontFamily = FontFamily(
                    Font(R.font.poppins_medium)
                )
            )
        },
        navigationIcon = {
            IconButton(onClick = { onBackPressed() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Arrow Back",
                    tint = colorResource(id = R.color.white)
                )
            }
        })
}