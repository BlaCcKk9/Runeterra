package com.lms.worldoflol.ui.screens.main.search.profile_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.lms.worldoflol.R
import com.lms.worldoflol.domain.model.remote.Match
import com.lms.worldoflol.ui.theme.textStyle14
import com.lms.worldoflol.ui.theme.textStyle16
import com.lms.worldoflol.utils.backgroundWithBorder

@Composable
fun MatchItem(
    match: Match,
    onMatchClicked: (Match) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
            .height(107.dp)
            .backgroundWithBorder(
                backgroundColor = 0xB30E141B,
                borderGradientColors = arrayListOf(0xFFCA9D4B, 0xFFEEE2CC),
                shouldGradientVeretical = false,
                borderWidth = 0.5.dp
            )
            .clickable { onMatchClicked(match) }
    ) {
        MatchItemLeftContent(match)
        MatchItemRightContent(match)
    }
}

@Composable
fun BoxScope.MatchItemLeftContent(match: Match) {
    Column(
        modifier = Modifier
            .padding(top = 10.dp, bottom = 10.dp, start = 10.dp)
            .align(Alignment.CenterStart)
    ) {
        Text(
            text = match.gameCondition,
            style = textStyle16(
                gradient = match.gameConditionGradient,
                fontWeight = 700
            ),
        )

        Spacer(modifier = Modifier.padding(top = 8.dp))

        Row(modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(match.championImageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .backgroundWithBorder(borderColor = match.gameConditionColor)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(Modifier.fillMaxHeight()) {
                val killDeathAssist = "${match.kill} / ${match.death} / ${match.assist}"
                Text(
                    text = killDeathAssist,
                    style = textStyle16(
                        gradient = listOf(0xFFF6C97F, 0xFFEEE2CC),
                        fontWeight = 700
                    ),
                    modifier = Modifier.align(Alignment.TopStart)
                )
                Row(
                    modifier = Modifier
                        .padding(bottom = 2.dp)
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Image(
                        painter = painterResource(id = match.laneImage),
                        modifier = Modifier.size(27.dp),
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        modifier = Modifier.padding(bottom = 4.dp),
                        text = match.lane,
                        style = textStyle14(0xFFEEE2CC)
                    )
                }
            }
        }

    }
}

@Composable
fun BoxScope.MatchItemRightContent(match: Match) {
    Row(
        modifier = Modifier.align(Alignment.CenterEnd),
        horizontalArrangement = Arrangement.End
    ) {
        Column(
            modifier = Modifier.padding(top = 15.dp, bottom = 15.dp, end = 3.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(text = match.gameType, style = textStyle14(0xFFCA9D4B))
            Spacer(modifier = Modifier.height(13.dp))
            Text(
                text = match.gameCreation,
                style = textStyle14(color = 0x80EEE2CC, fontStyle = FontStyle.Italic)
            )
            Spacer(modifier = Modifier.height(13.dp))
            Row(
                modifier = Modifier.height(21.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_clock),
                    modifier = Modifier.size(20.dp),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = match.gameDuration,
                    style = textStyle14(color = 0xFFEEE2CC, fontStyle = FontStyle.Italic)
                )
            }

        }

        Spacer(modifier = Modifier.width(10.dp))

        Spacer(
            modifier = Modifier
                .fillMaxHeight()
                .width(16.dp)
                .background(
                    color = Color(match.gameConditionColor),
                    shape = RoundedCornerShape(topEnd = 15.dp, bottomEnd = 15.dp)
                )
        )

    }
}