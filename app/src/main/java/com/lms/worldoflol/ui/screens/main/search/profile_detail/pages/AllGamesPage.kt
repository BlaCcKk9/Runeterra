package com.lms.worldoflol.ui.screens.main.search.profile_detail.pages

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lms.worldoflol.R
import com.lms.worldoflol.common.shimmerEffect
import com.lms.worldoflol.domain.model.remote.Match
import com.lms.worldoflol.ui.screens.main.search.profile_detail.components.MatchItem
import com.lms.worldoflol.ui.theme.skeleton_color_40
import com.lms.worldoflol.ui.theme.skeleton_color_60
import com.lms.worldoflol.ui.theme.textStyle12
import com.lms.worldoflol.ui.theme.textStyle16

val lazyColumnPadding = PaddingValues(top = 25.dp, start = 16.dp, end = 16.dp)


@Composable
fun MatchesPage(
    matches: List<Match?>,
    onSeeAllGamesClicked: () -> Unit,
    onMatchClicked: (Match) -> Unit,
) {
    val scrollState = rememberLazyListState()
    LazyColumn(
        state = scrollState,
        modifier = Modifier
            .padding(lazyColumnPadding)
            .fillMaxWidth()
            .fillMaxHeight(),
        contentPadding = PaddingValues(bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            MatchesPageHeader(onSeeAllGamesClicked = { onSeeAllGamesClicked() })
//            scrollState.lastVisibleItemIndex
        }

////        itemsIndexed(matches){ index, item ->
////            val alpha by animateFloatAsState(if (LaunchedEffect(scrollState) {
////                    snapshotFlow { scrollState.firstVisibleItemIndex }
////                        .collect { if (it <= index) 1f else 0f }
////                }))
//            MatchItem(
//                match = item!!,
//                modifier = Modifier.alpha(alpha),
//                onMatchClicked = { onMatchClicked(it) }
//            )
//        }
    }
}

@Composable
fun MatchesPageHeader(onSeeAllGamesClicked: () -> Unit) {
    Box(
        Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterStart),
            text = "Last 10 Games",
            style = textStyle16(color = 0xFFEEE2CC, fontStyle = FontStyle.Italic),
        )
        Row(modifier = Modifier
            .align(Alignment.CenterEnd)
            .clickable { onSeeAllGamesClicked() }
        ) {
            Text(
                text = "See All",
                style = textStyle12(0x66EEE2CC),
                modifier = Modifier.clickable { }
            )
            Spacer(modifier = Modifier.width(13.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_see_all_arrow_right),
                contentDescription = ""
            )
        }
    }
}

@Composable
fun AllGamesPageSkeleton() {
    Column(
        modifier = Modifier
            .padding(lazyColumnPadding)
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        MatchesPageHeaderSkeleton()
        MatchItemSkeleton()
    }
}

@Composable
fun MatchesPageHeaderSkeleton() {
    Box(
        Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Spacer(
            modifier = Modifier
                .height(15.dp)
                .width(101.dp)
                .shimmerEffect(
                    backgroundColor = skeleton_color_40,
                    shape = RoundedCornerShape(4.dp)
                )
                .align(Alignment.CenterStart)
        )

        Spacer(
            modifier = Modifier
                .height(15.dp)
                .width(46.dp)
                .shimmerEffect(
                    backgroundColor = skeleton_color_40,
                    shape = RoundedCornerShape(4.dp)
                )
                .align(Alignment.CenterEnd)
        )
    }
}


@Composable
fun MatchItemSkeleton() {
    for (item in 0 until 4)
        Box(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
                .height(107.dp)
                .shimmerEffect(
                    backgroundColor = skeleton_color_40,
                )
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp, start = 10.dp)
                    .align(Alignment.CenterStart)
            ) {
                Spacer(
                    modifier = Modifier
                        .height(21.dp)
                        .width(79.dp)
                        .shimmerEffect(
                            backgroundColor = skeleton_color_60,
                            shape = RoundedCornerShape(4.dp)
                        )
                )
                Spacer(modifier = Modifier.padding(top = 8.dp))
                Row(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                ) {
                    Spacer(
                        modifier = Modifier
                            .height(60.dp)
                            .width(60.dp)
                            .shimmerEffect(
                                backgroundColor = skeleton_color_60,
                            )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(Modifier.fillMaxHeight()) {
                        Spacer(
                            modifier = Modifier
                                .padding(top = 3.dp)
                                .height(15.dp)
                                .width(162.dp)
                                .shimmerEffect(
                                    backgroundColor = skeleton_color_60,
                                    shape = RoundedCornerShape(4.dp)
                                )
                                .align(Alignment.TopStart)
                        )
                        Spacer(
                            modifier = Modifier
                                .padding(bottom = 3.dp)
                                .height(15.dp)
                                .width(91.dp)
                                .shimmerEffect(
                                    backgroundColor = skeleton_color_60,
                                    shape = RoundedCornerShape(4.dp)
                                )
                                .align(Alignment.BottomStart)
                        )
                    }
                }
            }
        }
}

@Preview
@Composable
fun GamesSkeletonPreview() {
    AllGamesPageSkeleton()
}