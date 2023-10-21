package com.example.mymovieappjc.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mymovieappjc.R

@Composable
fun SubTitleComponentBar(
    subTitle: String,
    midTitle: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, top = 12.dp, end = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = subTitle,
            fontSize = 20.sp,
            color = colorResource(id = R.color.txt_col_1),
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxWidth()
                .height(30.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            colorResource(id = R.color.gradient_col_1),
                            colorResource(id = R.color.gradient_col_2)
                        )
                    ), shape = RoundedCornerShape(60.dp)
                )
        ) {
            Text(
                text = midTitle,
                fontSize = 17.sp,
                color = colorResource(id = R.color.txt_col_3),
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold
            )
        }
        ClickableText(
            text = AnnotatedString("See All"),
            onClick = { onClick }
        )
    }

}