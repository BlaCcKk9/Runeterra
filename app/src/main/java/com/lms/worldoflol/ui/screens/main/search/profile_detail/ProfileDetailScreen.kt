package com.lms.worldoflol.ui.screens.main.search.profile_detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.lms.worldoflol.R
import com.lms.worldoflol.common.ProfilePager
import com.lms.worldoflol.common.TabRow
import com.lms.worldoflol.domain.model.remote.Entry
import com.lms.worldoflol.domain.model.remote.Match
import com.lms.worldoflol.domain.model.remote.Summoner
import com.lms.worldoflol.ui.screens.main.profile.ProfileEvents
import com.lms.worldoflol.ui.screens.main.profile.components.ProfileHeaderContent
import com.lms.worldoflol.ui.screens.main.profile.firstCall
import com.lms.worldoflol.ui.screens.main.search.profile_detail.pages.MatchesPage
import com.lms.worldoflol.ui.screens.main.search.profile_detail.pages.AllGamesPageSkeleton
import com.lms.worldoflol.ui.screens.main.search.profile_detail.pages.RanksPage
import com.lms.worldoflol.ui.screens.main.search.profile_detail.pages.RanksPageSkeleton
import com.lms.worldoflol.ui.theme.textStyle18
import com.lms.worldoflol.utils.backgroundWithBorder
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun ProfileDetailScreen(
    viewModel: ProfileDetailViewModel = hiltViewModel(),
    summoner: Summoner?,
    onBackPressed: () -> Unit,
    navigateToMatchDetail: (Match) -> Unit,
) {

    val scope = rememberCoroutineScope()
    val state by viewModel.state.collectAsStateWithLifecycle(
        initialValue = ProfileDetailState(
            isEntnriesLoading = true,
            isMatchesLoading = true,
            summoner = summoner
        )
    )

    LaunchedEffect(key1 = state.entries?.isNotEmpty()) {
        if (summoner != null)
            scope.launch {
                viewModel.onEvent(ProfileDetailEvent.FetchProfile(summoner))
            }
    }

    if (summoner != null)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFF242731))
        ) {
            ProfileContent(
                entries = state.entries ?: listOf(),
                matches = state.matches ?: listOf(),
                isMatchLoading = state.isMatchesLoading,
                isEntriesLoading = state.isEntnriesLoading,
                summoner = summoner,
                onMatchClicked = { navigateToMatchDetail(it) },
                onBackPressed = { onBackPressed() }
            )
        }
    else Box(modifier = Modifier)
}

@OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)
@Composable
fun ProfileContent(
    entries: List<Entry?>,
    matches: List<Match?>,
    isMatchLoading: Boolean,
    isEntriesLoading: Boolean,
    summoner: Summoner,
    onMatchClicked: (Match) -> Unit,
    onBackPressed: () -> Unit
) {
    val pagerState = rememberPagerState(initialPage = 0)
    val profileTabs = listOf("Ranks", "All Games", "Live Game")
    val scope = rememberCoroutineScope()

    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 200.dp),
        painter = painterResource(id = R.drawable.profile_details_header_image),
        contentScale = ContentScale.FillBounds,
        contentDescription = ""
    )

    Spacer(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        Color(0x00242731),
                        Color(0xFF242731)
                    ),
                    endY = with(LocalDensity.current) {
                        188.dp.toPx()
                    }
                )
            )
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp)
                .size(50.dp)
                .backgroundWithBorder(backgroundColor = 0xFF0E141B, borderColor = 0x4DCA9D4B)
                .clickable { onBackPressed() },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_left),
                contentDescription = ""
            )
        }

        ProfileHeader(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            summoner = summoner
        )

        TabRow(
            tabs = profileTabs,
            pagerState = pagerState
        ) { tabIndex ->
            scope.launch { pagerState.animateScrollToPage(tabIndex) }
        }

        ProfilePager(
            count = profileTabs.size,
            pagerState = pagerState,
            onRanksPage = {
                val isLoading = !(!isEntriesLoading || entries.isNotEmpty())
                AnimatedVisibility(
                    visible = !isLoading,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    RanksPage(entries = entries)
                }
                if (isLoading) RanksPageSkeleton()

            },
            onAllGamesPage = {
                val isLoading = !(!isMatchLoading || matches.isNotEmpty())
                AnimatedVisibility(
                    visible = !isLoading,
                    enter = expandVertically(expandFrom = Alignment.Top) { 20 },
                    exit = fadeOut(),
                ) {
                    MatchesPage(
                        matches = matches,
                        onSeeAllGamesClicked = {},
                        onMatchClicked = { onMatchClicked(it) }
                    )
                }

                AnimatedVisibility(
                    visible = isLoading,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    AllGamesPageSkeleton()
                }
            },
            onLiveGamePage = { RanksPage(entries = entries) }
        )
    }
}

@Composable
fun ProfileHeader(
    modifier: Modifier = Modifier,
    summoner: Summoner,
) {
    Box(modifier) {
        ProfileHeaderContent(
            modifier = modifier,
            profileIconId = summoner.profileIconId.toString(),
            profileName = summoner.name
        )

        Text(
            text = summoner.summonerLevel.toString(),
            style = textStyle18(color = 0xFFEEE2CC),
            modifier = Modifier
                .padding(start = 46.dp)
                .size(width = 60.dp, height = 30.dp)
                .backgroundWithBorder(
                    backgroundColors = arrayListOf(0xFF0E141B, 0xFF242731),
                    borderGradientColors = 0xFFFF6C97F,
                    borderWidth = 0.5.dp
                )
                .padding(top = 3.dp)
                .align(Alignment.BottomStart)
        )
    }
}

@Preview
@Composable
fun ProfileHeaderPreview() {
    val summoner = Summoner(
        "name", "0", "name", "", 0, 0
    )
    ProfileHeader(summoner = summoner)
}


@Preview
@Composable
fun ProfileBodyPreview() {
    RanksPage(
        entries = listOf(
            null,
            null
        )
    )
}

@Preview
@Composable
fun AllGamesPagePreview() {
//    AllGamesPage()
}
