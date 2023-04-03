package com.lms.worldoflol.ui.screens.main.champions.champion_detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.lms.worldoflol.common.BackButton
import com.lms.worldoflol.common.ChampionsPager
import com.lms.worldoflol.common.TabRow
import com.lms.worldoflol.domain.model.remote.ChampionDetail
import com.lms.worldoflol.domain.model.remote.Skin
import com.lms.worldoflol.ui.screens.main.champions.champion_detail.pages.AbilitiesPage
import com.lms.worldoflol.ui.screens.main.champions.champion_detail.pages.OverviewPage
import com.lms.worldoflol.ui.screens.main.champions.champion_detail.pages.SkinsPage
import com.lms.worldoflol.ui.theme.gradientText
import com.lms.worldoflol.ui.theme.textStyle20
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun ChampionDetailsScreen(
    viewModel: ChampionDetailsViewModel = hiltViewModel(),
    navController: NavController,
    championName: String
) {

    val state by viewModel.state.collectAsStateWithLifecycle(
        initialValue = ChampionDetailsState(isLoading = true)
    )

    var selectedSkin by remember { mutableStateOf<Skin?>(null) }

    LaunchedEffect(key1 = true) {
        viewModel.getChampionDetails(championName)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF242731))
    ) {
        state.championDetail?.let {
            val skinImage = selectedSkin?.imageUrl ?: it.championImage
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(skinImage)
                    .crossfade(true)
                    .build(),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp)
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
                                280.dp.toPx()
                            }
                        )
                    )
            )

            ChampionDetailsContnent(
                championDetail = it,
                selectedSkin = selectedSkin
            ) { skin -> selectedSkin = skin }


            BackButton(
                modifier = Modifier.align(Alignment.TopStart),
                onBackPressed = { navController.navigateUp() }
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalPagerApi::class)
@Composable
fun ChampionDetailsContnent(
    championDetail: ChampionDetail,
    selectedSkin: Skin?,
    onSkinSelect: (Skin) -> Unit
) {

    val pagerState = rememberPagerState(initialPage = 0)
    val championTabs = listOf("Overview", "Abilities", "Skins")
    val scope = rememberCoroutineScope()

    Column(Modifier.fillMaxSize()) {
        Header(
            championName = championDetail.championName,
            championTitle = selectedSkin?.name ?: championDetail.championTitle
        )
        TabRow(
            tabs = championTabs,
            pagerState = pagerState
        ) { scope.launch { pagerState.animateScrollToPage(it) } }
        ChampionsPager(
            count = championTabs.size,
            pagerState = pagerState,
            onOverviewPage = { OverviewPage(championDetail) },
            onAbilitiesPage = { AbilitiesPage(championDetail) },
            onSkinsPage = {
                SkinsPage(championDetail.skins) {
                    onSkinSelect(it)
                }
            }
        )
    }
}

@Composable
fun Header(
    championName: String,
    championTitle: String,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(top = 190.dp),
            text = championName,
            style = gradientText(
                size = 32,
                color1 = 0xFFCA9D4B,
                color2 = 0xFFF6C97F,
                fontWeight = 700
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = championTitle,
            style = textStyle20(color = 0xFFEEE2CC, lineHeight = 20.sp)
        )
    }
}

