package com.lms.worldoflol.ui.screens.auth.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lms.worldoflol.ui.theme.textStyle20
import com.lms.worldoflol.utils.backgroundWithBorder

@Composable
fun StartButton(onStartClick: () -> Unit) {
    Spacer(modifier = Modifier.height(40.dp))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .backgroundWithBorder(
                backgroundColors = arrayListOf(0xFFF6C97F, 0xFFCA9D4B),
                borderGradientColors = 0x80242731
            )
            .clickable { onStartClick.invoke() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "START",
            modifier = Modifier.fillMaxWidth(),
            style = textStyle20(color = 0xFF242731, textAlign = TextAlign.Center)
        )
    }
}

@Preview
@Composable
fun StartButtonPreview() {
    StartButton() {}
}