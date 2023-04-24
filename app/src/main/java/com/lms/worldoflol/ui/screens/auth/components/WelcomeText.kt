package com.lms.worldoflol.ui.screens.auth.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lms.worldoflol.ui.theme.textStyle

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
        style = textStyle(
            size = 36.sp,
            gradient = listOf(0xFFF6C97F, 0xFFCA9D4B),
            fontWeight = 700,
            letterSpacing = 10.sp,
            lineHeight = 47.sp
        )
    )
}