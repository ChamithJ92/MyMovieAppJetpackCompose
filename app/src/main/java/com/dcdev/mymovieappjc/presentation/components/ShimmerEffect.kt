package com.dcdev.mymovieappjc.presentation.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.dcdev.mymovieappjc.R

fun Modifier.shimmerEffect() = composed {
    val transaction = rememberInfiniteTransition()
    val alpha = transaction.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        )
    ).value
    background(color = colorResource(id = R.color.purple_200).copy(alpha = alpha))
}

@Composable
fun MovieCardShimmerEffect(
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly)  {

            Box(
                modifier = Modifier
                    .width(160.dp)
                    .height(220.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .shimmerEffect())

            Spacer(modifier = modifier)
        
            Box(
                modifier = Modifier
                    .width(160.dp)
                    .height(220.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .shimmerEffect())

        }
}

