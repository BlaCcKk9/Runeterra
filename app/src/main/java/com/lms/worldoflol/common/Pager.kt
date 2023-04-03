package com.lms.worldoflol.common

import androidx.compose.runtime.Composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProfilePager(
    count: Int,
    pagerState: PagerState,
    onRanksPage: @Composable () -> Unit,
    onAllGamesPage: @Composable () -> Unit,
    onLiveGamePage: @Composable () -> Unit
) {
    HorizontalPager(
        count = count,
        state = pagerState
    ) { page ->
        when (page) {
            Pages.PAGE.first() -> { onRanksPage() }
            Pages.PAGE.second() -> { onAllGamesPage() }
            Pages.PAGE.third() -> { onLiveGamePage() }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ChampionsPager(
    count: Int,
    pagerState: PagerState,
    onOverviewPage: @Composable () -> Unit,
    onAbilitiesPage: @Composable () -> Unit,
    onSkinsPage: @Composable () -> Unit
) {
    HorizontalPager(
        count = count,
        state = pagerState
    ) { page ->
        when (page) {
            Pages.PAGE.first() -> { onOverviewPage() }
            Pages.PAGE.second() -> { onAbilitiesPage() }
            Pages.PAGE.third() -> { onSkinsPage() }
        }
    }
}

enum class Pages {
    PAGE {
        override fun first() = 0
        override fun second() = 1
        override fun third() = 2
    };

    abstract fun first(): Int
    abstract fun second(): Int
    abstract fun third(): Int
}
