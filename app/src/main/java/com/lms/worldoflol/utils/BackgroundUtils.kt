package com.lms.worldoflol.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.backgroundWithBorder(
    backgroundColor: Long = 0x00000000,
    borderColor: Long = 0x00000000,
    radius: Dp = 15.dp,
    borderWidth: Dp = 1.dp
) =
    clip(RoundedCornerShape(radius))
        .border(
            width = borderWidth,
            shape = RoundedCornerShape(radius),
            color = Color(borderColor)
        )
        .background(color = Color(backgroundColor))

fun Modifier.backgroundWithBorder(
    backgroundColor: Long = 0x00000000,
    borderGradientColors: ArrayList<Long>,
    radius: Dp = 15.dp,
    borderWidth: Dp = 1.dp,
    shouldGradientVeretical: Boolean = true
): Modifier {

    val brush = ArrayList<Color>()
    borderGradientColors.onEach { brush.add(Color(it)) }

    return clip(RoundedCornerShape(radius))
        .border(
            width = borderWidth,
            shape = RoundedCornerShape(radius),
            brush =  if (shouldGradientVeretical) Brush.verticalGradient(brush) else Brush.horizontalGradient(brush)
        )
        .background(color = Color(backgroundColor))
}

fun Modifier.backgroundWithBorder(
    backgroundColors: ArrayList<Long>,
    borderGradientColors: Long = 0x1ACA9D4B,
    radius: Dp = 15.dp,
    borderWidth: Dp = 1.dp
): Modifier {

    val brush = ArrayList<Color>()
    backgroundColors.onEach { brush.add(Color(it)) }

    return clip(RoundedCornerShape(radius))
        .border(
            width = borderWidth,
            shape = RoundedCornerShape(radius),
            color =  Color(borderGradientColors)
        )
        .background(brush = Brush.verticalGradient(brush))
}

fun Modifier.backgroundWithBorder(
    backgroundColors: ArrayList<Long>,
    borderGradientColors: ArrayList<Long>,
    radius: Dp = 15.dp,
    borderWidth: Dp = 1.dp,
    shouldGradientVeretical: Boolean = true
): Modifier {

    val backgroundBrush = ArrayList<Color>()
    backgroundColors.onEach { backgroundBrush.add(Color(it)) }

    val borderBrush = ArrayList<Color>()
    borderGradientColors.onEach { borderBrush.add(Color(it)) }

    return clip(RoundedCornerShape(radius))
        .border(
            width = borderWidth,
            shape = RoundedCornerShape(radius),
            brush =  if (shouldGradientVeretical) Brush.verticalGradient(borderBrush) else Brush.horizontalGradient(borderBrush)
        )
        .background(brush = Brush.verticalGradient(backgroundBrush))
}