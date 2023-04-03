package com.lms.worldoflol.ui.screens.main.search.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.lms.worldoflol.R
import com.lms.worldoflol.common.ErrorType
import com.lms.worldoflol.common.NoInternetConnectionScreen
import com.lms.worldoflol.domain.model.remote.Summoner
import com.lms.worldoflol.ui.theme.textStyle18


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SearchResultContent(
    searchResult: Summoner?,
    isLoading: Boolean,
    error: ErrorType?,
    onRefresh: () -> Unit,
    onSummonerClick: (summoner: Summoner) -> Unit,
) {

    AnimatedContent(
        targetState = true,
        transitionSpec = {
            scaleIn() + fadeIn() with
                    scaleOut() + fadeOut()
        }
    ) {
        when {
            searchResult != null -> {
                SummonerItem(
                    summoner = searchResult,
                    icon = R.drawable.ic_summoner_arroow_right,
                    onRemoveClick = {},
                ) { summoner -> onSummonerClick(summoner) }
            }

            isLoading -> {
                Box(Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.surface,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            error != null -> {
                when (error) {
                    is ErrorType.NoInternetConnection -> {
                        NoInternetConnectionScreen { onRefresh() }
                    }

                    is ErrorType.NotFound -> {
                        NotFoundContent()
                    }

                    else -> {}
                }
            }
        }
    }
}

@Composable
fun NotFoundContent() {
    Column(
        modifier = Modifier
            .padding(top = 140.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "No Result Found",
            style = textStyle18(0x66EEE2CC)
        )
        Spacer(modifier = Modifier.height(13.dp))
        Image(
            modifier = Modifier.size(width = 131.dp, height = 168.dp),
            painter = painterResource(id = R.drawable.ic_no_result_found),
            contentScale = ContentScale.FillBounds,
            contentDescription = "",
        )
    }
}