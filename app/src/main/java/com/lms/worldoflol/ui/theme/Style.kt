package com.lms.worldoflol.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


fun selectRegionTextStyle(ifBlank: Boolean) = TextStyle(
    textAlign = if (ifBlank) TextAlign.Start else TextAlign.Center,
    fontFamily = QuadrataFamily,
    fontWeight = FontWeight(400),
    fontSize = 18.sp,
    color = startButtonTextColor
)


@OptIn(ExperimentalTextApi::class)
fun profileRankedInfoRankTextStyle(colorGradient: List<Color>) = TextStyle(
    textAlign = TextAlign.Start,
    fontFamily = QuadrataFamily,
    fontWeight = FontWeight(700),
    fontSize = 20.sp,
    brush = Brush.verticalGradient(colorGradient)
)
@OptIn(ExperimentalTextApi::class)
fun textStyle(
    size: TextUnit = 16.sp,
    color: Long = 0x00000000,
    gradient: List<Long> = listOf(),
    fontWeight: Int = 400,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    lineHeight: TextUnit = TextUnit.Unspecified,
    textAlign: TextAlign = TextAlign.Center,
    fontStyle: FontStyle = FontStyle.Normal
) = if (gradient.isEmpty()) {
    TextStyle(
        textAlign = textAlign,
        fontFamily = QuadrataFamily,
        fontWeight = FontWeight(fontWeight),
        fontStyle = fontStyle,
        fontSize = size,
        letterSpacing = letterSpacing,
        lineHeight = lineHeight,
        color = Color(color)
    )
} else {
    val gradientColors = ArrayList<Color>()
    gradient.forEach { gradientColors.add(Color(it)) }
    TextStyle(
        textAlign = textAlign,
        fontFamily = QuadrataFamily,
        fontWeight = FontWeight(fontWeight),
        fontStyle = fontStyle,
        fontSize = 16.sp,
        letterSpacing = letterSpacing,
        lineHeight = lineHeight,
        brush = Brush.verticalGradient(gradientColors)
    )
}

@OptIn(ExperimentalTextApi::class)
fun textStyle16(
    color: Long = 0x00000000,
    gradient: List<Long> = listOf(),
    fontWeight: Int = 400,
    textAlign: TextAlign = TextAlign.Center,
    fontStyle: FontStyle = FontStyle.Normal
) = if (gradient.isEmpty()) {
    TextStyle(
        textAlign = textAlign,
        fontFamily = QuadrataFamily,
        fontWeight = FontWeight(fontWeight),
        fontStyle = fontStyle,
        fontSize = 16.sp,
        color = Color(color)
    )
} else {
    val gradientColors = ArrayList<Color>()
    gradient.forEach { gradientColors.add(Color(it)) }
    TextStyle(
        textAlign = textAlign,
        fontFamily = QuadrataFamily,
        fontWeight = FontWeight(fontWeight),
        fontStyle = fontStyle,
        fontSize = 16.sp,
        brush = Brush.verticalGradient(gradientColors)
    )
}

fun textStyle10(
    color: Long,
    fontWeight: Int = 400,
    textAlign: TextAlign = TextAlign.Center,
    fontStyle: FontStyle = FontStyle.Normal
) = TextStyle(
    textAlign = textAlign,
    fontFamily = QuadrataFamily,
    fontWeight = FontWeight(fontWeight),
    fontStyle = fontStyle,
    fontSize = 10.sp,
    color = Color(color)
)

fun textStyle12(
    color: Long,
    fontWeight: Int = 400,
    textAlign: TextAlign = TextAlign.Center,
    fontStyle: FontStyle = FontStyle.Normal
) = TextStyle(
    textAlign = textAlign,
    fontFamily = QuadrataFamily,
    fontWeight = FontWeight(fontWeight),
    fontStyle = fontStyle,
    fontSize = 12.sp,
    color = Color(color)
)

fun textStyle13(
    color: Long,
    fontWeight: Int = 400,
    textAlign: TextAlign = TextAlign.Center,
    fontStyle: FontStyle = FontStyle.Normal
) = TextStyle(
    textAlign = textAlign,
    fontFamily = QuadrataFamily,
    fontWeight = FontWeight(fontWeight),
    fontStyle = fontStyle,
    fontSize = 13.sp,
    color = Color(color)
)


fun textStyle14(
    color: Long,
    fontWeight: Int = 400,
    textAlign: TextAlign = TextAlign.Center,
    fontStyle: FontStyle = FontStyle.Normal
) = TextStyle(
    textAlign = textAlign,
    fontFamily = QuadrataFamily,
    fontWeight = FontWeight(fontWeight),
    lineHeight = 20.sp,
    fontStyle = fontStyle,
    fontSize = 14.sp,
    color = Color(color)
)

fun textStyle15(
    color: Long,
    fontWeight: Int = 400,
    textAlign: TextAlign = TextAlign.Center,
    fontStyle: FontStyle = FontStyle.Normal
) = TextStyle(
    textAlign = textAlign,
    fontFamily = QuadrataFamily,
    fontWeight = FontWeight(fontWeight),
    fontStyle = fontStyle,
    fontSize = 15.sp,
    color = Color(color)
)

fun textStyle18(
    color: Long,
    fontWeight: Int = 400,
    textAlign: TextAlign = TextAlign.Center,
    fontStyle: FontStyle = FontStyle.Normal
) = TextStyle(
    textAlign = textAlign,
    fontFamily = QuadrataFamily,
    fontWeight = FontWeight(fontWeight),
    fontStyle = fontStyle,
    fontSize = 18.sp,
    color = Color(color)
)

fun textStyle20(
    color: Long,
    fontWeight: Int = 400,
    textAlign: TextAlign = TextAlign.Center,
    fontStyle: FontStyle = FontStyle.Normal,
    lineHeight: TextUnit = TextUnit.Unspecified
) = TextStyle(
    textAlign = textAlign,
    fontFamily = QuadrataFamily,
    fontWeight = FontWeight(fontWeight),
    fontStyle = fontStyle,
    fontSize = 20.sp,
    lineHeight = lineHeight,
    color = Color(color)
)

fun textStyle24(
    color: Long,
    fontWeight: Int = 400,
    textAlign: TextAlign = TextAlign.Center,
    fontStyle: FontStyle = FontStyle.Normal
) = TextStyle(
    textAlign = textAlign,
    fontFamily = QuadrataFamily,
    fontWeight = FontWeight(fontWeight),
    fontStyle = fontStyle,
    fontSize = 24.sp,
    color = Color(color)
)

fun textStyle28(
    color: Long,
    fontWeight: Int = 400,
    textAlign: TextAlign = TextAlign.Center,
    fontStyle: FontStyle = FontStyle.Normal
) = TextStyle(
    textAlign = textAlign,
    fontFamily = QuadrataFamily,
    fontWeight = FontWeight(fontWeight),
    fontStyle = fontStyle,
    fontSize = 28.sp,
    color = Color(color)
)

fun textStyle32(
    color: Long,
    fontWeight: Int = 400,
    textAlign: TextAlign = TextAlign.Center,
    fontStyle: FontStyle = FontStyle.Normal
) = TextStyle(
    textAlign = textAlign,
    fontFamily = QuadrataFamily,
    fontWeight = FontWeight(fontWeight),
    fontStyle = fontStyle,
    fontSize = 32.sp,
    color = Color(color)
)

@OptIn(ExperimentalTextApi::class)
fun gradientText(
    size: Int,
    color1: Long,
    color2: Long,
    fontWeight: Int = 400,
    textAlign: TextAlign = TextAlign.Center,
    fontStyle: FontStyle = FontStyle.Normal
) = TextStyle(
    textAlign = textAlign,
    fontFamily = QuadrataFamily,
    fontWeight = FontWeight(fontWeight),
    fontStyle = fontStyle,
    fontSize = size.sp,
    lineHeight = 42.sp,
    brush = Brush.verticalGradient(
        listOf(
            Color(color1),
            Color(color2)
        )
    )
)

// Span Styles
val winLoseSpanStyle = SpanStyle(
    color = Color(0xFFEEE2CC),
    fontSize = 14.sp,
    fontFamily = QuadrataFamily
)

val WSpanStyle = SpanStyle(
    color = Color(0xCC0ACBE6),
    fontSize = 14.sp,
    fontFamily = QuadrataFamily
)

val LSpanStyle = SpanStyle(
    color = Color(0xCCF12242),
    fontSize = 14.sp,
    fontFamily = QuadrataFamily
)
