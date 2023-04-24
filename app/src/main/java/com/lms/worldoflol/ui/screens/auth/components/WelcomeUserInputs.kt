package com.lms.worldoflol.ui.screens.auth.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lms.worldoflol.ui.theme.textStyle20
import com.lms.worldoflol.utils.backgroundWithBorder

@Composable
fun WelcomeUserInputs(
    selectedRegion: String,
    summonerName: String,
    shouldShowFindSummoner: Boolean,
    onIsSummonerClicked: () -> Unit,
    onIsNotSummonerClicked: () -> Unit,
    onSelectRegionClicked: (String) -> Unit,
    onStartClicked: (String, String) -> Unit,
) {
//    if (shouldShowFindSummoner)
}

@Composable
fun ColumnScope.IsSummonerOrNotContent(
    onIsSummonerClicked: () -> Unit,
    onIsNotSummonerClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .backgroundWithBorder(
                backgroundColors = arrayListOf(0xFFCA9D4B, 0xFFF6C97F),
                borderGradientColors = 0x80242731,
                borderWidth = 0.dp
            )
            .clickable { onIsSummonerClicked() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "I am a Summoner",
            modifier = Modifier.fillMaxWidth(),
            style = textStyle20(color = 0xFF242731, textAlign = TextAlign.Center)
        )
    }

}