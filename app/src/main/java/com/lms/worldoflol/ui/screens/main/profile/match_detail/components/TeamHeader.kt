package com.lms.worldoflol.ui.screens.main.profile.match_detail.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lms.worldoflol.domain.model.local.TeamDetail
import com.lms.worldoflol.ui.theme.textStyle

@Composable
fun TeamHeader(team: TeamDetail) {
    Spacer(modifier = Modifier.height(20.dp))
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.align(Alignment.CenterStart),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = team.name + " ",
                style = textStyle(
                    size = 16.sp,
                    color = 0xFFEEE2CC,
                ),
            )
            Text(
                text = team.stats,
                style = textStyle(
                    size = 14.sp,
                    color = 0xFFF6C97F,
                ),
            )
        }
        Text(
            modifier = Modifier.align(Alignment.CenterEnd),
            text = team.gameCondition,
            style = textStyle(
                size = 14.sp,
                color = 0xFFF6C97F,
            ),
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
}