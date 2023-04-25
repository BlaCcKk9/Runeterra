package com.lms.worldoflol.ui.screens.auth.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lms.worldoflol.R
import com.lms.worldoflol.ui.theme.selectRegionTextStyle
import com.lms.worldoflol.ui.theme.startButtonTextColor
import com.lms.worldoflol.utils.backgroundWithBorder

@Composable
fun SelectRegionButton1(
    selectedRegionName: () -> String,
    onSelectRegion: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .backgroundWithBorder(
                backgroundColors = arrayListOf(0xFFF6C97F,0xFFCA9D4B),
                borderGradientColors = 0x99242731
            )
            .clickable { onSelectRegion.invoke() },
    ) {
        Text(
            text = selectedRegionName().ifBlank { "Select Region" },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(start = if (selectedRegionName().isNotBlank()) 38.dp else 0.dp),
            style = selectRegionTextStyle(selectedRegionName().isNotBlank())
        )
        Icon(
            modifier = Modifier
                .wrapContentSize()
                .padding(end = 28.dp)
                .align(Alignment.CenterEnd),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_down),
            tint = startButtonTextColor,
            contentDescription = ""
        )
    }
}

@Preview
@Composable
fun SelectRegionPreview() {
    SelectRegionButton1({ "Select Region" }) {}
}