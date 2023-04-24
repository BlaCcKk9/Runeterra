package com.lms.worldoflol.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.lms.worldoflol.ui.theme.textStyle
import com.lms.worldoflol.utils.backgroundWithBorder

private val ButtonHeight = 60.dp

@Composable
fun PrimaryButton(
    text: String,
    disabled: Boolean = false,
    modifier: Modifier = Modifier,
    padding: Dp = 16.dp,
    onClick: () -> Unit
) {
    var buttonColor = if (!disabled) {
        arrayListOf(0xFFCA9D4B, 0xFFF6C97F)
    } else arrayListOf(0x80CA9D4B, 0x80F6C97F)

    Box(
        modifier = modifier
            .padding(horizontal = padding)
            .height(ButtonHeight)
            .backgroundWithBorder(
                backgroundColors = buttonColor,
                borderWidth = 0.dp
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = textStyle(color = 0xFF242731)
        )
    }
}

@Composable
fun SecondaryButton(
    text: String,
    disabled: Boolean = false,
    modifier: Modifier = Modifier,
    padding: Dp = 16.dp,
    onClick: () -> Unit
) {
    var buttonColor = if (!disabled) {
        arrayListOf(0x990E141B, 0xFF242731)
    } else arrayListOf(0x800E141B, 0x80242731)

    Box(
        modifier = modifier
            .padding(horizontal = padding)
            .height(ButtonHeight)
            .backgroundWithBorder(
                backgroundColors = buttonColor,
                borderGradientColors = arrayListOf(0x80CA9D4B, 0x80CA9D4B)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = textStyle(color = 0xFFEEE2CC)
        )
    }
}

@Preview
@Composable
fun PrimaryButtonPreview() {
    PrimaryButton(
        text = "I am a Summoner",
        modifier = Modifier.fillMaxWidth()
    ) {}
}

@Preview
@Composable
fun PrimaryButtonWrapPreview() {
    Row(
        Modifier
            .fillMaxWidth()
            .height(200.dp), verticalAlignment = Alignment.CenterVertically) {
        PrimaryButton(
            text = "I am a Summoner",
            disabled = true,
            modifier = Modifier.weight(1f)
        ) {}
        PrimaryButton(
            text = "I am a Summoner",
            modifier = Modifier.weight(1f)
        ) {}
    }
}

@Preview
@Composable
fun SecondaryButtonPreview() {
    SecondaryButton(
        text = "I am not Summoner",
        modifier = Modifier.fillMaxWidth()
    ) {}
}

@Preview
@Composable
fun SecondaryButtonWrapPreview() {
    Row(
        Modifier
            .fillMaxWidth()
            .height(200.dp), verticalAlignment = Alignment.CenterVertically) {
        SecondaryButton(
            text = "I am a Summoner",
            disabled = true,
            modifier = Modifier.weight(1f)
        ) {}
        SecondaryButton(
            text = "I am a Summoner",
            modifier = Modifier.weight(1f)
        ) {}
    }
}