package com.lms.worldoflol.ui.screens.main.profile

import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.lms.worldoflol.common.ErrorType
import com.lms.worldoflol.common.NoInternetConnectionScreen
import com.lms.worldoflol.common.ProfilePager
import com.lms.worldoflol.common.TabRow
import com.lms.worldoflol.domain.model.remote.Entry
import com.lms.worldoflol.domain.model.remote.Match
import com.lms.worldoflol.domain.model.remote.Summoner
import com.lms.worldoflol.ui.MainActivity
import com.lms.worldoflol.ui.screens.main.profile.components.LogoutDialog
import com.lms.worldoflol.ui.screens.main.profile.components.ProfileHeaderContent
import com.lms.worldoflol.ui.screens.main.profile.components.ProfileMenu
import com.lms.worldoflol.ui.screens.main.profile.components.ProfileMenuButton
import com.lms.worldoflol.ui.screens.main.search.profile_detail.pages.MatchesPage
import com.lms.worldoflol.ui.screens.main.search.profile_detail.pages.AllGamesPageSkeleton
import com.lms.worldoflol.ui.screens.main.search.profile_detail.pages.RanksPage
import com.lms.worldoflol.ui.theme.textStyle18
import com.lms.worldoflol.utils.backgroundWithBorder
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.S)
@OptIn(
    ExperimentalLifecycleComposeApi::class,
    ExperimentalAnimationApi::class
)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    summoner: Summoner?,
    bottomBarVisibility: (isVisible: Boolean) -> Unit,
    navigateToMatchDetail: (Match) -> Unit,
    navigateToAllGames: (String, String) -> Unit
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle(
        initialValue = ProfileState(isLoading = true)
    )

    LaunchedEffect(key1 = state.matches == null) {
        if (firstCall) {
            firstCall = false
            if (summoner != null)
                scope.launch {
                    viewModel.onEvent(ProfileEvents.FetchProfile(summoner))
                }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF242731))
    ) {
        if (summoner != null) {
            bottomBarVisibility(true)
            if (state.entries != null) {
                ProfileContent(
                    entries = state.entries ?: listOf(),
                    matches = state.matches ?: listOf(),
                    isMatchLoading = state.isMatchesLoading,
                    summoner = state.summoner,
                    onMatchClicked = { navigateToMatchDetail(it) },
                    onSeeAllClicked = { navigateToAllGames(summoner.region, summoner.puuid) },
                    onRefresh = {
                        scope.launch {
                            viewModel.onEvent(ProfileEvents.Refresh(summoner))
                        }
                    },
                    onLogout = {
                        firstCall = true
                        scope.cancel()
                        val intent = Intent(context, MainActivity::class.java)
                        context.startActivity(intent)
                    }
                )
            }
        } else Box(modifier = Modifier)

        AnimatedVisibility(
            visible = state.error != null,
            enter = scaleIn(spring(stiffness = Spring.StiffnessLow)),
            exit = scaleOut()
        ) {
            when (state.error) {
                is ErrorType.NoInternetConnection -> {
                    NoInternetConnectionScreen {
                        bottomBarVisibility(state.error == null)
                        scope.launch {
                            viewModel.onEvent(ProfileEvents.Refresh(summoner!!))
                        }
                    }
                }

                is ErrorType.NotFound -> {
                    Log.e("NotFound", "NotFound")
                }

                is ErrorType.HTTP -> {
                    Log.e("HTTP", "HTTP")
                }

                else -> {
                    Log.e("else", "else")
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProfileContent(
    entries: List<Entry?>,
    matches: List<Match?>,
    isMatchLoading: Boolean,
    summoner: Summoner,
    onMatchClicked: (Match) -> Unit,
    onSeeAllClicked: () -> Unit,
    onRefresh: () -> Unit,
    onLogout: () -> Unit
) {
    val pagerState = rememberPagerState(initialPage = 0)
    val profileTabs = listOf("Ranks", "All Games", "Live Game")
    var showLogoutDialog by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    if (showLogoutDialog) {
        LogoutDialog(
            onCancel = { showLogoutDialog = false },
            onLogout = { onLogout() }
        )
    }

    Image(
        painter = painterResource(id = R.drawable.profile_details_header_image),
        contentScale = ContentScale.FillBounds,
        contentDescription = "",
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
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
        ProfileHeader(
            summoner = summoner,
            onRefresh = { onRefresh() },
            onLogout = { showLogoutDialog = true },
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
        )
        TabRow(
            tabs = profileTabs,
            pagerState = pagerState
        ) { tabIndex ->
            scope.launch {
                pagerState.animateScrollToPage(tabIndex)
            }
        }
        ProfilePager(
            count = profileTabs.size,
            pagerState = pagerState,
            onRanksPage = {
                RanksPage(entries = entries)
            },
            onAllGamesPage = {
                val isLoading = !(!isMatchLoading || matches.isNotEmpty())
//                AnimatedVisibility(
//                    visible = !isLoading,
//                    enter = expandVertically(expandFrom = Alignment.Top) { 20 },
//                    exit = shrinkVertically(animationSpec = tween()) { fullHeight ->
//                        fullHeight / 2
//                    }
//                ) {
//                    MatchesPage(
//                        matches = matches,
//                        onSeeAllGamesClicked = { onSeeAllClicked() },
//                        onMatchClicked = { onMatchClicked(it) }
//                    )
//                }

                MatchesPage(
                    matches = matches,
                    onSeeAllGamesClicked = { onSeeAllClicked() },
                    onMatchClicked = { onMatchClicked(it) }
                )

                AnimatedVisibility(
                    visible = isLoading,
                    exit = fadeOut()
                ) {
                    AllGamesPageSkeleton()
                }
            },
            onLiveGamePage = {
                RanksPage(entries = entries)
            }
        )
    }
}

@Composable
fun ProfileHeader(
    modifier: Modifier = Modifier,
    summoner: Summoner,
    onRefresh: () -> Unit,
    onLogout: () -> Unit
) {
    val showMenu = remember { mutableStateOf(false) }

    Box(modifier) {
        ProfileHeaderContent(
            modifier = modifier.height(215.dp),
            profileIconId = summoner.profileIconId.toString(),
            profileName = summoner.name
        )

        ProfileMenuButton(
            modifier = Modifier.align(Alignment.TopEnd),
            isVisible = showMenu.value,
            buttonIcon = R.drawable.ic_profile_menu,
            onClick = { showMenu.value = true }
        )

        ProfileMenu(
            isVisible = showMenu.value,
            modifier = Modifier.align(Alignment.TopEnd),
            onClose = { showMenu.value = false },
            onRefresh = { onRefresh() },
            onLogout = { onLogout() }
        )

        Text(
            text = summoner.summonerLevel.toString(),
            style = textStyle18(color = 0xFFEEE2CC),
            modifier = Modifier
                .padding(start = 46.dp)
                .size(width = 60.dp, height = 30.dp)
                .backgroundWithBorder(
                    backgroundColors = arrayListOf(0xFF0E141B, 0xFF242731),
                    borderGradientColors = 0xFFF6C97F,
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
        "name", "", "0", "500", 0, 0
    )
    ProfileHeader(
        summoner = summoner,
        onRefresh = { },
        onLogout = { }
    )
}


//@Preview
//@Composable
//fun ProfileBodyPreview() {
//    ProfileBody(
//        modifier = Modifier
//            .padding(top = 45.dp)
//            .fillMaxWidth()
//            .wrapContentHeight(),
//        entries = listOf(
//            null,
//            null
//        )
//    )
//}