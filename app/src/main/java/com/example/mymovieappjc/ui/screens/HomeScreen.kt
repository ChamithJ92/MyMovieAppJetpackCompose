package com.example.mymovieappjc.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.mymovieappjc.components.PagerWithEffect

@Composable
fun HomeScreen(){
//    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
//        Text(text = "Home", color = Color.Red, fontSize = 25.sp)
//    }
    Column(modifier = Modifier.fillMaxSize()) {
        PagerWithEffect()
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}