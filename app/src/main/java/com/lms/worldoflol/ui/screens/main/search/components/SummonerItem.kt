package com.lms.worldoflol.ui.screens.main.search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.lms.worldoflol.R
import com.lms.worldoflol.domain.model.remote.Summoner
import com.lms.worldoflol.ui.theme.textStyle10
import com.lms.worldoflol.ui.theme.textStyle18
import com.lms.worldoflol.utils.backgroundWithBorder

@Composable
fun SummonerItem(
    summoner: Summoner,
    icon: Int = R.drawable.ic_remove_recent,
    ifLast: Boolean = false,
    modifier: Modifier = Modifier,
    onRemoveClick: (summoner: Summoner) -> Unit,
    onItemClick: (summoner: Summoner) -> Unit,
) {
    var bottomPaddinng = if (ifLast) 16.dp else 0.dp

    Row(
        modifier = modifier
            .padding(top = 16.dp, bottom = bottomPaddinng)
            .fillMaxWidth()
            .height(80.dp)
            .backgroundWithBorder(backgroundColor = 0x660E141B)
            .clickable { onItemClick(summoner) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(80.dp)
                .backgroundWithBorder(borderColor = 0xFFCA9D4B),
            contentAlignment = Alignment.BottomCenter
        ) {
            SubcomposeAsyncImage(
                model = "http://ddragon.leagueoflegends.com/cdn/12.21.1/img/profileicon/${summoner.profileIconId}.png",
                loading = {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.surface,
                        modifier = Modifier.size(30.dp).align(Alignment.Center)
                    )
                },
                contentDescription = "summoner_icon",
                modifier = Modifier.fillMaxSize(),
            )

            Text(
                modifier = Modifier
                    .height(18.dp)
                    .width(40.dp)
                    .backgroundWithBorder(
                        backgroundColor = 0x660E141B,
                        borderColor = 0xFFF6C97F,
                        borderWidth = 0.5.dp
                    )
                    .padding(top = 2.dp),
                text = summoner.summonerLevel.toString(),
                style = textStyle10(0xFFEEE2CC)
            )
        }

        Text(
            modifier = Modifier.padding(start = 16.dp, top = 16.dp),
            text = summoner.name,
            style = textStyle18(0xFFEEE2CC)
        )

        Box(modifier = Modifier.padding(end = 16.dp).fillMaxSize()) {
            Icon(
                modifier = Modifier.align(Alignment.CenterEnd)
                    .clickable { onRemoveClick(summoner) },
                painter = painterResource(id = icon),
                tint = Color(0xB3CA9D4B),
                contentDescription = "remove_resent_searches"
            )
        }
    }
}


@Preview
@Composable
fun SummonerItemPreview() {
    SummonerItem(
        summoner = Summoner(
            id = "HanmaBaki69",
            name = "HanmaBaki69",
            summonerLevel = 398,
            profileIconId = 45,
            region = "",
            puuid = ""
        ),
        modifier = Modifier,
        onRemoveClick = {}
    ) {}
}