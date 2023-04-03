package com.lms.worldoflol.ui.screens.main.champions.champion_detail.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lms.worldoflol.utils.backgroundWithBorder

fun Modifier.championDetailsHeader() =
    padding(horizontal = 16.dp)
        .fillMaxWidth()
        .wrapContentHeight()
        .backgroundWithBorder(
            backgroundColors = arrayListOf(0xB30E141B, 0xB3242731),
            borderGradientColors = arrayListOf(0x80CA9D4B, 0xFF242731)
        )
