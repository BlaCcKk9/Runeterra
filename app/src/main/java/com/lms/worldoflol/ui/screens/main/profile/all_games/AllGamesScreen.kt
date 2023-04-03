package com.lms.worldoflol.ui.screens.main.profile.all_games

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lms.worldoflol.common.BackButton
import com.lms.worldoflol.common.ErrorType
import com.lms.worldoflol.common.NoInternetConnectionScreen
import com.lms.worldoflol.common.RuneterraContent
import com.lms.worldoflol.domain.model.local.MatchDetail
import com.lms.worldoflol.domain.model.remote.Match
import com.lms.worldoflol.ui.screens.main.profile.all_games.components.FilterButtonContainer
import com.lms.worldoflol.ui.screens.main.search.profile_detail.components.MatchItem
import com.lms.worldoflol.ui.theme.LSpanStyle
import com.lms.worldoflol.ui.theme.WSpanStyle
import com.lms.worldoflol.ui.theme.textStyle
import com.lms.worldoflol.ui.theme.winLoseSpanStyle

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun AllGamesScreen(
    viewModel: AllGamesViewModel = hiltViewModel(),
    region: String,
    puuid: String,
) {
    val state by viewModel.state.collectAsStateWithLifecycle(
        initialValue = AllGamesState(isLoading = true)
    )

    LaunchedEffect(key1 = true) {
        viewModel.onEvent(AllGamesEvents.GetMatches(region, puuid))
    }

    RuneterraContent(
        response = state.matches,
        isLoading = state.isLoading,
        error = state.error,
        onBackPressed = { },
        Skeleton = { },
    ) { AllGames(matches = { state.matches!! }) }
}

@Composable
fun AllGames(matches: () -> List<Match?>) {
    Column {
        AllGamesHeader()
        AllGamesContent(matches)
    }
}

@Composable
fun AllGamesContent(matches: () -> List<Match?>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp)
    ) {
        item {
            FilterGameSection()
        }
        items(matches().size) { pos ->
            MatchItem(
                match = matches()[pos]!!,
                onMatchClicked = { }
            )
        }
    }
}

@Composable
fun FilterGameSection() {
    Box(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            FilterButtonContainer(text = "All", isSelected = true) {}
            FilterButtonContainer(text = "Ranked") {}
            FilterButtonContainer(text = "Normal") {}
        }

        FilterButtonContainer(
            isSelected = false,
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {}
    }
}


@Composable
fun AllGamesHeader() {
    Box(
        modifier = Modifier
            .shadow(
                elevation = 20.dp,
                shape = RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp)
            )
            .fillMaxWidth()
            .height(86.dp)
            .clip(RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp))
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp),
                brush = Brush.verticalGradient(
                    listOf(
                        Color(0x660E141B),
                        Color(0x66CA9D4B),
                    )
                )
            )
            .background(color = Color(0xFF0E141B))
    ) {
        Column(
            modifier = Modifier
                .padding(start = 82.dp)
                .wrapContentSize()
                .align(Alignment.CenterStart),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "All Game",
                style = textStyle(color = 0xFFF6C97F)
            )
            Spacer(modifier = Modifier.height(11.dp))

            Text(buildAnnotatedString {
                withStyle(style = winLoseSpanStyle) { append("Last 20 Games: ${9}") }
                withStyle(style = WSpanStyle) { append(" W ") }
                withStyle(style = winLoseSpanStyle) { append("11 ") }
                withStyle(style = LSpanStyle) { append(" L") }
            })
        }

        Box(
            modifier = Modifier
                .padding(end = 16.dp)
                .wrapContentSize()
                .align(Alignment.CenterEnd)
        ) {
            CircularProgressIndicator(
                progress = 0.5f,
                color = Color(0xFF0ACBE6),
                strokeWidth = 6.dp,
                modifier = Modifier
                    .size(50.dp)
                    .drawBehind {
                        drawCircle(
                            Color.Red,
                            radius = size.width / 2 - 6.dp.toPx() / 2,
                            style = Stroke(6.dp.toPx())
                        )
                    },
            )
            Text(
                text = "45%",
                style = textStyle(
                    size = 14.sp,
                    color = 0xFFEEE2CC
                ),
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview
@Composable
fun AllGamesScreenPreview() {
//    AllGamesScreen()
}