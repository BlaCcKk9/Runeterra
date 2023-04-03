package com.lms.worldoflol.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lms.worldoflol.R
import com.lms.worldoflol.ui.theme.textStyle16
import com.lms.worldoflol.ui.theme.textStyle24
import com.lms.worldoflol.utils.backgroundWithBorder

@Composable
fun NoInternetConnectionScreen(onRefresh: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(0xFF242731))
            .padding(horizontal = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Whoops!", style = textStyle24(color = 0xFFF6C97F, fontWeight = 700))
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = "Slow or no internet connection",
            style = textStyle16(
                color = 0x80EEE2CC,
                textAlign = TextAlign.Center
            )
        )
        Text(
            text = "Please check your internet settings.",
            style = textStyle16(
                color = 0x80EEE2CC,
                textAlign = TextAlign.Center
            )
        )
        Spacer(modifier = Modifier.height(40.dp))
        Image(
            modifier = Modifier
                .size(width = 170.dp, height = 146.dp)
                .aspectRatio(1f),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.ic_error),
            contentDescription = "",
        )
        Spacer(modifier = Modifier.height(50.dp))
        RefreshButton { onRefresh() }
    }
}

@Composable
private fun RefreshButton(onClick: () -> Unit = { }) {
    Box(
        modifier = Modifier
            .height(55.dp)
            .width(140.dp)
            .backgroundWithBorder(
                backgroundColors = arrayListOf(0x660E141B, 0x99242731),
                borderGradientColors = 0x66CA9D4B
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Refresh",
            style = textStyle16(color = 0xB3EEE2CC)
        )
    }
}


@Preview
@Composable
fun ErrorScreenPreview() {
    NoInternetConnectionScreen {}
}