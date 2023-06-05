package com.lms.worldoflol.ui.screens.auth.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lms.worldoflol.R
import com.lms.worldoflol.ui.theme.QuadrataFamily
import com.lms.worldoflol.ui.theme.textStyle

@OptIn(ExperimentalTextApi::class)
@Composable
fun WelcomeText() {
    Text(
        text = "Welcome to",
        style = textStyle(
            size = 32.sp,
            color = 0xFFEEE2CC,
        )
    )
    Text(
        text = "RUNETERRA",
        fontWeight = FontWeight(700),
        fontSize = 36.sp,
        letterSpacing = 5.sp,
        fontFamily = QuadrataFamily,
        style = TextStyle(
           brush = Brush.verticalGradient(
               listOf(
                   Color(0xFFF6C97F),
                   Color(0xFFCA9D4B)
               )
           ),
            fontStyle = FontStyle(R.font.font_quadrata_medium),
        ),
        modifier = Modifier.wrapContentHeight()
    )
}

@Preview
@Composable
fun WelcomeTextPreview() {
    WelcomeText()
}