package ru.kinesis.tmdb.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerMovieCard(
    colors: List<Color>,
    cardHeight: Dp
){
    val brush = Brush.linearGradient(
        colors,
        start = Offset(100f, 100f),
        end = Offset(200f, 200f)
    )
    Surface(shape = MaterialTheme.shapes.small) {
        Spacer(
            modifier = Modifier
                .height(cardHeight)
                .fillMaxWidth()
                .padding(all = 8.dp)
                .background(brush = brush)
        )
    }
}

/*
object ShimmerAnimationDefinitions{
    enum class AnimationState{
        START, END
    }
    val xShimmerPropKey = FloatPropKey()
}
*/
