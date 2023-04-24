package com.lms.worldoflol.ui.screens.main.champions.champion_detail.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lms.worldoflol.domain.model.remote.ChampionDetail
import com.lms.worldoflol.ui.screens.main.champions.champion_detail.components.championDetailsHeader
import com.lms.worldoflol.ui.theme.textStyle13
import com.lms.worldoflol.ui.theme.textStyle14
import com.lms.worldoflol.ui.theme.textStyle15

@Composable
fun OverviewPage(championDetail: ChampionDetail) {
    Column(
        modifier = Modifier
            .padding(top = 25.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Row(modifier = Modifier.championDetailsHeader()) {
            OverViewHeaderContent(championDetail)
        }
        Spacer(modifier = Modifier.height(20.dp))
        ChampionDetailLore(
            lore = championDetail.lore,
            blurb = championDetail.blurb,
        )
    }
}

@Composable
fun RowScope.OverViewHeaderContent(championDetail: ChampionDetail) {
    Column(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .weight(1f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Role", style = textStyle13(color = 0x80EEE2CC))
        Spacer(modifier = Modifier.height(12.dp))
        Image(
            modifier = Modifier.size(40.dp, 40.dp),
            painter = painterResource(id = championDetail.roleImage),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(text = championDetail.role, style = textStyle13(color = 0xFFF6C97F))
    }

    Column(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .weight(1f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Difficulty", style = textStyle13(color = 0x80EEE2CC))
        Spacer(modifier = Modifier.height(12.dp))
        Box(
            modifier = Modifier.height(40.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.height(7.dp),
                painter = painterResource(id = championDetail.difficultyImage),
                contentDescription = ""
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(text = championDetail.difficulty, style = textStyle13(color = 0xFFF6C97F))

    }
}

@Composable
fun ChampionDetailLore(
    lore: String,
    blurb: String,
) {
    var readMoreVisible by remember {
        mutableStateOf(true)
    }

    Text(
        modifier = Modifier.padding(start = 32.dp),
        text = "Lore",
        style = textStyle15(color = 0x80EEE2CC)
    )
    Spacer(modifier = Modifier.height(8.dp))
    if (readMoreVisible)
        BlurbText(
            blurb = blurb,
            modifier = Modifier.clickable { readMoreVisible = false }
        )
    else LoreText(lore = lore)
    Spacer(modifier = Modifier.height(40.dp))
}

@Composable
fun LoreText(lore: String) {
    Text(
        modifier = Modifier
            .padding(horizontal = 32.dp)
            .fillMaxWidth(),
        text = lore,
        style = textStyle14(color = 0xFFEEE2CC, textAlign = TextAlign.Start)
    )
}

@Composable
fun BlurbText(
    modifier: Modifier = Modifier,
    blurb: String
) {
    Text(
        text = buildAnnotatedString {
            append(blurb)
            withStyle(
                style = SpanStyle(
                    color = Color(0xFFF6C97F),
                    fontSize = 16.sp
                )
            ) {
                append(" Read more")
            }
        },
        style = textStyle14(color = 0xFFEEE2CC, textAlign = TextAlign.Start),
        modifier = modifier
            .padding(horizontal = 32.dp)
            .fillMaxWidth()
    )
}