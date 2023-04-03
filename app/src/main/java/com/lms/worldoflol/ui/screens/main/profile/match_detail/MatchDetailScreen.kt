package com.lms.worldoflol.ui.screens.main.profile.match_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.lms.worldoflol.R
import com.lms.worldoflol.common.RuneterraContent
import com.lms.worldoflol.domain.model.local.MatchDetail
import com.lms.worldoflol.domain.model.local.Participant
import com.lms.worldoflol.domain.model.local.TeamDetail
import com.lms.worldoflol.domain.model.remote.Summoner
import com.lms.worldoflol.ui.screens.main.profile.match_detail.components.MatchDetailHeader
import com.lms.worldoflol.ui.screens.main.profile.match_detail.components.MatchDetailSkeleton
import com.lms.worldoflol.ui.screens.main.profile.match_detail.components.TeamHeader
import com.lms.worldoflol.ui.theme.textStyle
import com.lms.worldoflol.utils.backgroundWithBorder


@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun MatchDetailScreen(
    viewModel: MatchDetailViewModel = hiltViewModel(),
    region: String,
    matchId: String,
    puuid: String,
    onBackPressed: () -> Unit,
    navigateDetailScreen: (summoner: Summoner) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle(
        initialValue = MatchDetailState(isLoading = true)
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(
            MatchDetailEvents.FetchMatchDetail(
                region = region,
                matchId = matchId,
                puuid = puuid
            )
        )
    }

    LaunchedEffect(key1 = state.summoner != null) {
        state.summoner = null
    }

    RuneterraContent(
        response = state.matchDetail,
        isLoading = state.isLoading,
        error = state.error,
        onBackPressed = { onBackPressed() },
        Skeleton = { MatchDetailSkeleton() },
    ) {
        MatchDetail(
            matchDetail = state.matchDetail!!,
            onMemberClicked = {
                viewModel.onEvent(
                    MatchDetailEvents.GetSummoner(
                        summonerName = it.summonerName,
                        region = region
                    )
                )
            }
        )
    }

    if (state.summoner != null)
        navigateDetailScreen(state.summoner!!)
}

@Composable
fun MatchDetail(
    matchDetail: MatchDetail,
    onMemberClicked: (Participant) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 20.dp, top = 86.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(matchDetail.teams) { team ->
            MatchDetailItem(team = team) { member ->
                onMemberClicked(member)
            }
        }
    }
    MatchDetailHeader(matchDetail)
}

@Composable
fun MatchDetailItem(
    team: TeamDetail?,
    onMemberClicked: (Participant) -> Unit
) {
    team?.let {
        Column(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            TeamHeader(team = it)
            TeamMembers(it.members) { onMemberClicked(it) }
        }
    }
}

@Composable
fun TeamMembers(
    members: List<Participant>,
    onMemberClicked: (Participant) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        for (member in members)
            MemberItem(member = member) { onMemberClicked(it) }
    }
}

@Composable
fun MemberItem(
    member: Participant,
    onClick: (Participant) -> Unit
) {
    val memberItemBackground = if (member.isMe) 0xFF0E141B else 0xB30E141B
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(108.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(
                color = Color(memberItemBackground),
                shape = RoundedCornerShape(15.dp)
            )
            .clickable { onClick(member) },
        contentAlignment = Alignment.Center
    ) {
        MemberLeftContent(
            modifier = Modifier
                .padding(start = 16.dp)
                .wrapContentSize()
                .align(Alignment.CenterStart),
            member = member
        )
        MemberRightContent(
            modifier = Modifier
                .padding(end = 16.dp)
                .fillMaxHeight()
                .wrapContentHeight()
                .align(Alignment.CenterEnd),
            member = member
        )
    }
}

@Composable
fun MemberLeftContent(
    modifier: Modifier = Modifier,
    member: Participant,
) {
    Column(modifier = modifier) {
        Text(
            text = member.summonerName,
            style = textStyle(
                size = 14.sp,
                color = 0xFFEEE2CC,
                fontWeight = 700
            ),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Box {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(member.championImage)
                        .crossfade(true)
                        .build(),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(50.dp)
                        .backgroundWithBorder(borderColor = member.conditionColor)
                )
                Text(
                    modifier = Modifier
                        .wrapContentSize()
                        .backgroundWithBorder(
                            backgroundColor = 0x66242731,
                            borderColor = member.conditionColor,
                            borderWidth = 0.5.dp
                        )
                        .padding(top = 2.dp, bottom = 2.dp, start = 6.dp, end = 6.dp)
                        .align(Alignment.BottomCenter),
                    text = member.champLevel.toString(),
                    style = textStyle(
                        size = 8.sp,
                        color = 0xFFEEE2CC
                    )
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                for (spell in member.summonerSpells)
                    MemberCastsImageContainer(spell)
            }
            Spacer(modifier = Modifier.width(4.dp))
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                for (rune in member.runes)
                    MemberCastsImageContainer(rune)
            }

        }
    }
}

@Composable
fun MemberRightContent(
    modifier: Modifier = Modifier,
    member: Participant,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = "${member.kills} / ${member.deaths} / ${member.assists}",
            style = textStyle(
                size = 14.sp,
                gradient = listOf(0xFFF6C97F, 0xFFEEE2CC),
                fontWeight = 700
            ),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            for (item in member.items)
                MemberCastsImageContainer(imageUrl = item)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                text = "CS ${member.cs}",
                style = textStyle(
                    size = 14.sp,
                    color = 0xFFEEE2CC,
                ),
            )
            Spacer(modifier = Modifier.width(8.dp))
            ImageContainer(image = member.laneImage)
        }
    }
}

@Composable
fun MemberCastsImageContainer(imageUrl: String) {
    if (imageUrl != "")
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .placeholder(R.drawable.ic_item_placeholder)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(22.dp)
                .clip(RoundedCornerShape(8.dp))
        )
    else
        Image(
            painter = painterResource(id = R.drawable.ic_item_placeholder),
            modifier = Modifier.size(22.dp),
            contentDescription = ""
        )
}

@Composable
fun ImageContainer(image: Int) {
    Image(
        painter = painterResource(id = image),
        modifier = Modifier.size(22.dp),
        contentDescription = ""
    )
}