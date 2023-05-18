package com.lms.worldoflol.ui.screens.main.search

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lms.worldoflol.R
import com.lms.worldoflol.common.ErrorType
import com.lms.worldoflol.domain.model.remote.Summoner
import com.lms.worldoflol.ui.screens.main.search.components.SearchResultContent
import com.lms.worldoflol.ui.screens.main.search.components.SearchTextField
import com.lms.worldoflol.ui.screens.main.search.components.SummonerItem
import com.lms.worldoflol.ui.screens.main.search.helper.getSearchResultAnimation
import com.lms.worldoflol.ui.theme.textStyle12
import com.lms.worldoflol.ui.theme.textStyle16
import com.lms.worldoflol.ui.theme.textStyle18
import com.lms.worldoflol.utils.backgroundWithBorder
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    scope: CoroutineScope = rememberCoroutineScope(),
    navigateDetailScreen: (summoner: Summoner) -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        viewModel.onEvent(SearchEvents.GetRecentSearches(""))
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF242731))
            .padding(top = 20.dp, start = 16.dp, end = 16.dp)
    ) {
        SearchBody(
            modifier = Modifier
                .padding(top = 100.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            onSearchResult = state.onSearchResult,
            showTitle = state.showTitle,
            recentSearches = state.summoners,
            result = state.summoner,
            isLoading = state.isLoading,
            error = state.error,
            onRemove = { viewModel.onEvent(SearchEvents.DeleteSummoner(it)) },
            onDeleteAll = { viewModel.onEvent(SearchEvents.DeleteAll) },
            onSummonerClick = { navigateDetailScreen(it) }
        )

        SearchTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
                .backgroundWithBorder(backgroundColor = 0xCC0E141B, borderColor = 0x4DCA9D4B)
                .padding(horizontal = 16.dp),
            scope = scope,
            region = state.selectedRegion,
            onSummonerNameChanged = { viewModel.onEvent(SearchEvents.GetRecentSearches(name = it.text)) },
            onSearchClick = { region, summonerName ->
                viewModel.onEvent(
                    SearchEvents.GetSummoner(
                        summonerName = summonerName,
                        region = region
                    )
                )
            }
        )
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SearchBody(
    modifier: Modifier,
    onSearchResult: Boolean,
    showTitle: Boolean,
    result: Summoner?,
    isLoading: Boolean,
    error: ErrorType?,
    recentSearches: List<Summoner>,
    onDeleteAll: () -> Unit,
    onRemove: (summoner: Summoner) -> Unit,
    onSummonerClick: (summoner: Summoner) -> Unit
) {
    val density = LocalDensity.current
    var indicatorWith by remember {
        mutableStateOf(0.dp)
    }

    Column {
        if (showTitle) {
            Box(modifier = modifier, contentAlignment = Alignment.BottomCenter) {
                Text(
                    text = if (onSearchResult) "Result" else "Recent Search",
                    style = textStyle16(0xB3EEE2CC),
                    modifier = Modifier.align(Alignment.CenterStart)
                )
                Text(
                    text = if (onSearchResult) "" else "Delete All",
                    style = textStyle12(0x66EEE2CC),
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .onGloballyPositioned {
                            with(density) { indicatorWith = it.size.width.toDp() }
                        }
                        .clickable { onDeleteAll() }
                )
                Divider(
                    modifier = Modifier
                        .width(indicatorWith)
                        .align(Alignment.BottomEnd),
                    thickness = 0.5.dp,
                    color = Color(0x33CA9D4B)
                )
            }
        }

        Box {
            AnimatedContent(
                targetState = onSearchResult,
                transitionSpec = { getSearchResultAnimation(targetState) }
            ) { shouldShowSearchResultContent ->
                if (shouldShowSearchResultContent)
                    SearchResultContent(
                        searchResult = result,
                        isLoading = isLoading,
                        error = error,
                        onRefresh = {},
                        onSummonerClick = { onSummonerClick(it) }
                    )
                else
                    RecentSearchContent(
                        recentSearches = recentSearches,
                        onRemove = { onRemove(it) },
                        onRecentSearchClick = { onSummonerClick(it) }
                    )
            }
        }
    }
}

@Composable
fun RecentSearchContent(
    recentSearches: List<Summoner>,
    onRemove: (summoner: Summoner) -> Unit,
    onRecentSearchClick: (summoner: Summoner) -> Unit
) {
    if (recentSearches.isNotEmpty())
        RecentSearchList(
            recentSearches = recentSearches,
            onRemove = { onRemove(it) },
            onItemClick = { onRecentSearchClick(it) }
        )
    else EmptyRecentSearchContent()
}

@Composable
fun RecentSearchList(
    recentSearches: List<Summoner>,
    onRemove: (summoner: Summoner) -> Unit,
    onItemClick: (summoner: Summoner) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        reverseLayout = true,
        userScrollEnabled = true
    ) {
        items(recentSearches.size, key = { it }) { index ->
            val ifLast = index == 0
            SummonerItem(
                summoner = recentSearches[index],
                ifLast = ifLast,
                onRemoveClick = { onRemove(it) },
                onItemClick = { onItemClick(it) }
            )
        }
    }
}

@Composable
fun EmptyRecentSearchContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 140.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search_text_field),
                tint = Color(0xFFCA9D4B),
                contentDescription = "",
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Search Summoner", style = textStyle18(0x66EEE2CC))
        }
        Spacer(modifier = Modifier.height(13.dp))
        Image(
            modifier = Modifier.size(176.dp, 184.dp),
            painter = painterResource(id = R.drawable.ic_search_summoner),
            contentScale = ContentScale.FillBounds,
            contentDescription = ""
        )
    }
}


@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen {}
}