package com.lms.worldoflol.common

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.lms.worldoflol.R
import com.lms.worldoflol.ui.screens.main.champions.champion_detail.components.drawColoredShadow
import com.lms.worldoflol.ui.theme.textStyle
import com.lms.worldoflol.utils.backgroundWithBorder

private val ButtonHeight = 60.dp
private val PrimaryButtonColors = arrayListOf(0xFFCA9D4B, 0xFFF6C97F)
private val SecondaryButtonColors = arrayListOf(0x990E141B, 0xFF242731)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    enable: Boolean = true,
    enableTrailingIcon: Boolean = false,
    padding: Dp = 16.dp,
    onClick: () -> Unit
) {
    val alpha = if (enable) 1f else 0.5f
    Box(
        modifier = modifier
            .padding(horizontal = padding)
            .height(ButtonHeight)
            .alpha(alpha)
            .drawColoredShadow(
                color = Color(0xFF0E141B),
                alpha = 1f,
                shadowRadius = 12.dp
            )
            .backgroundWithBorder(
                backgroundColors = PrimaryButtonColors,
                borderWidth = 0.dp
            )
            .clickable(enable) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        ButtonContent(
            text = text,
            textColor = 0xFF242731,
            enableTrailingIcon = enableTrailingIcon,
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    text: String,
    enable: Boolean = true,
    enableTrailingIcon: Boolean = false,
    padding: Dp = 16.dp,
    onClick: () -> Unit
) {
    val alpha = if (enable) 1f else 0.5f
    Box(
        modifier = modifier
            .padding(horizontal = padding)
            .height(ButtonHeight)
            .alpha(alpha)
            .drawColoredShadow(
                color = Color(0xFF0E141B),
                alpha = 1f,
                shadowRadius = 12.dp
            )
            .backgroundWithBorder(
                backgroundColors = SecondaryButtonColors,
                borderGradientColors = arrayListOf(0x80CA9D4B, 0x80CA9D4B)
            )
            .clickable(enable) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        ButtonContent(
            text = text,
            textColor = 0xFFEEE2CC,
            enableTrailingIcon = enableTrailingIcon,
        )
    }
}

@Composable
fun SelectRegionButton(
    regionName: String = "",
    isSelectedRegionEmpty: Boolean = false,
    onClick: (String) -> Unit
) {
    val text = regionName.ifEmpty { "Select Region" }
    val borderColor: Long = if (isSelectedRegionEmpty)
        0x80F12242 else 0x80CA9D4B

    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .height(ButtonHeight)
            .backgroundWithBorder(
                backgroundColors = SecondaryButtonColors,
                borderGradientColors = borderColor
            )
            .padding(horizontal = 24.dp)
            .clickable { onClick(regionName) },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = textStyle(color = 0xFFEEE2CC),
            modifier = Modifier.align(Alignment.CenterStart)
        )
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_down),
            tint = Color(0xFFCA9D4B),
            contentDescription = "arrow_down_icon",
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}

@Composable
fun ButtonContent(
    text: String,
    textColor: Long,
    enableTrailingIcon: Boolean,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = text,
            style = textStyle(color = textColor)
        )
        if (enableTrailingIcon) {
            Spacer(Modifier.width(15.dp))
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_see_all_arrow_right),
                contentDescription = "arrow_right_icon"
            )
        }
    }
}

@Preview
@Composable
fun PrimaryButtonPreview() {
    PrimaryButton(
        text = "I am a Summoner",
        enable = false,
        modifier = Modifier.fillMaxWidth()
    ) {}
}

@Preview
@Composable
fun PrimaryButtonWrapPreview() {
    Row(
        Modifier
            .fillMaxWidth()
            .height(200.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        PrimaryButton(
            text = "I am a Summoner",
            enable = false,
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
        enable = true,
        modifier = Modifier.fillMaxWidth()
    ) {}
}

@Preview
@Composable
fun SecondaryButtonWrapPreview() {
    Row(
        Modifier
            .fillMaxWidth()
            .height(200.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        SecondaryButton(
            text = "I am a Summoner",
            enable = false,
            enableTrailingIcon = true,
            padding = 5.dp,
            modifier = Modifier.weight(1f)
        ) {}
        SecondaryButton(
            text = "I am a Summoner",
            padding = 5.dp,
            modifier = Modifier.weight(1f)
        ) {}
    }
}