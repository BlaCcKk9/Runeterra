package com.lms.worldoflol.ui.screens.main.profile.match_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lms.worldoflol.domain.model.local.MatchDetail
import com.lms.worldoflol.ui.theme.textStyle

@Composable
fun MatchDetailHeader(matchDetail: MatchDetail?) {
    matchDetail?.let {
        Box(
            modifier = Modifier
                .shadow(5.dp)
                .fillMaxWidth()
                .height(86.dp)
                .clip(RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp))
                .background(
                    shape = RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp),
                    color = Color(it.gameDetail.gameConditionColor)
                ),
            contentAlignment = Alignment.Center
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(106.dp)
                    .clip(RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp))
                    .background(Color(0x59242731))
            )
            Column(
                modifier = Modifier
                    .padding(start = 82.dp)
                    .wrapContentSize()
                    .align(Alignment.CenterStart),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = it.gameDetail.gameCondition,
                    style = textStyle(
                        size = 20.sp,
                        gradient = it.gameDetail.gameConditionGradient,
                        fontWeight = 700
                    ),
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = it.gameDetail.championName,
                    style = textStyle(
                        size = 16.sp,
                        color = 0xFFEEE2CC,
                    ),
                )
            }

            Column(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .wrapContentSize()
                    .align(Alignment.CenterEnd),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = it.gameDetail.gameType,
                    style = textStyle(
                        size = 14.sp,
                        color = 0xFFF6C97F,
                    ),
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row{
                    Text(
                        text = it.gameDetail.gameCreation + " | ",
                        style = textStyle(
                            size = 14.sp,
                            color = 0x80EEE2CC,
                            fontStyle = FontStyle.Italic
                        ),
                    )
                    Text(
                        text = it.gameDetail.gameDuration,
                        style = textStyle(
                            size = 14.sp,
                            color = 0xFFEEE2CC,
                            fontStyle = FontStyle.Italic
                        ),
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun MatchDetaiHeaderPreview() {
    MatchDetailHeader(null)
}