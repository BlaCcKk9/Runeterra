 package com.lms.worldoflol.ui.screens.auth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lms.worldoflol.R
import com.lms.worldoflol.common.NoInternetConnectionScreen
import com.lms.worldoflol.common.ErrorType
import com.lms.worldoflol.common.RuneterraBottomSheet
import com.lms.worldoflol.domain.model.remote.Summoner
import com.lms.worldoflol.ui.screens.auth.components.*
import com.lms.worldoflol.ui.screens.auth.components.SelectRegionButton
import com.lms.worldoflol.ui.theme.textStyle24
import com.lms.worldoflol.utils.backgroundWithBorder

@OptIn(
    ExperimentalMaterialApi::class,
    ExperimentalLifecycleComposeApi::class, ExperimentalAnimationApi::class
)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navigateMainScreen: (summoner: Summoner) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val selecteableRegionName = remember { derivedStateOf { state.selectedRegionName } }
    val startBottomSheet = remember { derivedStateOf { state.isSelectRegionClicked } }
    var accountNameNotFound by remember { mutableStateOf(false) }


    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed,
        animationSpec = tween(durationMillis = 500)
    )
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
    val bottomSheet = RuneterraBottomSheet(sheetState)


    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetShape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
        sheetBackgroundColor = Color(0x800E141B),
        sheetPeekHeight = 0.dp,
        sheetGesturesEnabled = false,
        sheetElevation = 0.dp,
        sheetContent = {
            RegionsBottomSheet(
                onCloseClick = {
                    viewModel.onEvent(
                        LoginEvent.OnRegionClick(selecteableRegionName.value)
                    )
                },
                onSelectRegion = { regionName ->
                    viewModel.onEvent(
                        LoginEvent.OnRegionClick(regionName)
                    )
                }
            )
        },
    ) {
        bottomSheet.BottomSheetUpdate(ifExpand = { startBottomSheet.value })

        AnimatedVisibility(
            visible = state.error != null,
            enter = scaleIn(spring(stiffness = Spring.StiffnessLow)),
            exit = scaleOut()
        ) {
            when (state.error) {
                is ErrorType.NoInternetConnection -> {
                    NoInternetConnectionScreen {
                        viewModel.onEvent(LoginEvent.OnRefresh)
                    }
                }

                is ErrorType.NotFound -> {
                    accountNameNotFound = true
                }

                else -> {}
            }

        }

        LoginScreenContent(
            isLoading = state.isLoading,
            isAccountNotFound = { accountNameNotFound },
            isSelectRegionClicked = { startBottomSheet.value },
            selectedRegionName = { selecteableRegionName.value },
            onSelectRegionButtonClicked = { viewModel.onEvent(LoginEvent.OnSelectRegionClick) },
            onStartButtonClicked = { summonerName ->
                viewModel.onEvent(
                    LoginEvent.OnLoginClick(
                        region = state.selectedRegionName,
                        summonerName = summonerName
                    ) { summoner -> navigateMainScreen(summoner) }
                )
            })
    }
}


@OptIn(ExperimentalAnimationApi::class)
@ExperimentalMaterialApi
@Composable
fun LoginScreenContent(
    isLoading: Boolean,
    isAccountNotFound: () -> Boolean,
    isSelectRegionClicked: () -> Boolean,
    selectedRegionName: () -> String,
    onSelectRegionButtonClicked: () -> Unit,
    onStartButtonClicked: (summonerName: String) -> Unit,
) {
    var summonerName by remember { mutableStateOf(TextFieldValue("")) }
    val padding = remember { Animatable(260f) }

    LaunchedEffect(key1 = isSelectRegionClicked()) {
        padding.animateTo(
            targetValue = if (isSelectRegionClicked()) 80f else if (selectedRegionName().isNotBlank()) 180f else 260f,
            animationSpec = tween(durationMillis = 500)
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LoginBackground()

        Image(
            modifier = Modifier
                .padding(
                    start = 60.dp,
                    end = 60.dp,
                    top = padding.value.dp
                )
                .fillMaxWidth()
                .height(90.dp),
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "",
            contentScale = ContentScale.Inside
        )

        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .wrapContentSize()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            Content(
                isSelectRegionClicked = { isSelectRegionClicked() },
                selectedRegionName = { selectedRegionName() },
                summonerName = summonerName,
                isAccountNotFound = isAccountNotFound,
                onSelectRegion = { onSelectRegionButtonClicked.invoke() },
                onStartButtonClicked = { onStartButtonClicked(summonerName.text) },
                onNameValueChange = { summonerName = it }
            )
        }

        AnimatedVisibility(
            modifier = Modifier.align(Alignment.Center),
            visible = isLoading,
            enter = scaleIn(animationSpec = spring(stiffness = Spring.StiffnessLow)),
            exit = scaleOut()
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier.align(Alignment.Center)
            )
        }

    }
}

@Composable
fun Content(
    isSelectRegionClicked: () -> Boolean,
    selectedRegionName: () -> String,
    summonerName: TextFieldValue,
    isAccountNotFound: () -> Boolean,
    onSelectRegion: () -> Unit,
    onStartButtonClicked: () -> Unit,
    onNameValueChange: (name: TextFieldValue) -> Unit,

    ) {
    AnimatedVisibility(
        visible = !isSelectRegionClicked(),
        enter = fadeIn(animationSpec = tween(durationMillis = 1000)),
        exit = fadeOut(animationSpec = tween(durationMillis = 500))
    ) {
        Column {
            SelectRegionButton(
                selectedRegionName = { selectedRegionName() },
                onSelectRegion = { onSelectRegion.invoke() }
            )
            if (selectedRegionName().isNotBlank()) {
                Spacer(modifier = Modifier.height(20.dp))
                SummonerNameTextField(
                    summonerName = summonerName,
                    isAccountNotFound = isAccountNotFound,
                    onSummonerNameChanged = onNameValueChange,
                )
                StartButton { onStartButtonClicked.invoke() }
            }
        }
    }
}


@Composable
fun RegionsBottomSheet(
    onCloseClick: () -> Unit,
    onSelectRegion: (regionName: String) -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .backgroundWithBorder(
                borderGradientColors = arrayListOf(0xFFCA9D4B,0x80242731),
                borderWidth =  0.5.dp
            )
    ) {
        RegionsBottomSheetHeader(
            Modifier.fillMaxWidth().height(75.dp),
            onCloseClick = { onCloseClick.invoke() }
        )
        RegionsBottomSheetList(onRegionClick = {
            onSelectRegion.invoke(it.name)
        })

        Spacer(modifier = Modifier.height(35.dp))
    }
}

@Composable
fun RegionsBottomSheetHeader(modifier: Modifier, onCloseClick: () -> Unit) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.ic_close_button),
            contentDescription = "close",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .clip(RoundedCornerShape(10.dp))
                .clickable { onCloseClick.invoke() }
                .padding(20.dp)
        )
        Text(
            text = "Select Region",
            style = textStyle24(color = 0xFFF6C97F),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
                .align(Alignment.BottomCenter)
        )
    }
    Divider(modifier = Modifier.fillMaxWidth(), thickness = 0.5.dp, color = Color(0x33CA9D4B))
}

@Composable
fun RegionsBottomSheetList(onRegionClick: (region: Regions) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        userScrollEnabled = false
    ) {
        items(Regions.size, key = { it }) {
            RegionItem(item = Regions.list[it]) { region ->
                onRegionClick.invoke(region)
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {

}