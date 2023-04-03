package com.lms.worldoflol.ui.screens.main.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.lms.worldoflol.R
import com.lms.worldoflol.domain.model.remote.Entry
import com.lms.worldoflol.ui.theme.LSpanStyle
import com.lms.worldoflol.ui.theme.WSpanStyle
import com.lms.worldoflol.ui.theme.profileRankedInfoRankTextStyle
import com.lms.worldoflol.ui.theme.textStyle14
import com.lms.worldoflol.ui.theme.textStyle16
import com.lms.worldoflol.ui.theme.winLoseSpanStyle
import com.lms.worldoflol.utils.backgroundWithBorder
import com.lms.worldoflol.utils.tierIcon

@Composable
fun EntryItem(entry: Entry?, position: Int) {
    EntryItemTitle(
        modifier = Modifier.fillMaxWidth(),
        position = position
    )
    Spacer(modifier = Modifier.height(6.dp))
    EntryItemContent(
        tierIcon = entry?.tierIcon ?: R.drawable.ic_tier_unranked,
        tier = entry?.tier ?: "UNRANKED",
        tierTextColor = entry?.tierTexColor ?: listOf(
            Color(0xFFA09B8C),
            Color(0x00D9D9D9)
        ),
        rank = entry?.rank ?: "",
        leaguePoints = entry?.leaguePoints ?: "0",
        wins = entry?.wins ?: "0",
        losses = entry?.losses ?: "0",
        winRate = entry?.winRate ?: "%"
    )
    Spacer(modifier = Modifier.height(25.dp))
}

@Composable
private fun EntryItemTitle(modifier: Modifier, position: Int) {
    Text(
        text = if (position == 0) "Ranked Solo/Dou" else "Ranked Flex",
        style = textStyle16(
            color = 0xFFEEE2CC,
            textAlign =  TextAlign.Start,
            fontStyle = FontStyle.Italic
        ),
        modifier = modifier
    )
}

@Composable
fun EntryItemContent(
    tierIcon: Int,
    tier: String,
    tierTextColor: List<Color>,
    rank: String,
    leaguePoints: String,
    wins: String,
    losses: String,
    winRate: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 120.dp)
            .backgroundWithBorder(
                backgroundColor = 0xB30E141B,
                borderGradientColors = arrayListOf(0xFFCA9D4B, 0xFFEEE2CC),
                borderWidth = 0.5.dp,
                shouldGradientVeretical = false
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = tierIcon),
            contentDescription = "",
            modifier = Modifier.tierIcon(tier),
        )
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(vertical = 19.dp)
        ) {
            Text(
                text = "$tier $rank",
                style = profileRankedInfoRankTextStyle(tierTextColor)
            )
            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "$leaguePoints LP",
                style = textStyle14(color = 0xFFEEE2CC, textAlign = TextAlign.Start)
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(buildAnnotatedString {
                withStyle(style = winLoseSpanStyle) {
                    append(wins)
                }
                withStyle(style = WSpanStyle) {
                    append(" W ")
                }
                withStyle(style = winLoseSpanStyle) {
                    append(losses)
                }
                withStyle(style = LSpanStyle) {
                    append(" L ")
                }
                withStyle(style = winLoseSpanStyle) {
                    append(winRate)
                }
            })
        }
    }
}