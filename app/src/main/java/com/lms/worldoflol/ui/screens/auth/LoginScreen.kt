package com.lms.worldoflol.ui.screens.auth

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lms.worldoflol.R
import com.lms.worldoflol.common.ErrorType
import com.lms.worldoflol.domain.model.remote.Summoner
import com.lms.worldoflol.ui.screens.auth.components.*
import com.lms.worldoflol.ui.theme.textStyle18
import com.lms.worldoflol.ui.theme.textStyle24
import com.lms.worldoflol.utils.backgroundWithBorder
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun LoginScreen(
    navigateMainScreen: (summoner: Summoner?) -> Unit
) {
    val viewModel = hiltViewModel<LoginViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LoginContent(
        state = state,
        onEvent = viewModel::onEvent,
        onContinue = { navigateMainScreen(it) }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun LoginContent(
    state: LoginState,
    onEvent: (LoginEvent) -> Unit,
    onContinue: (Summoner?) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.Expanded },
        animationSpec = tween(500),
        skipHalfExpanded = true
    )
    val keyboardController = LocalSoftwareKeyboardController.current!!
    val focusManager = LocalFocusManager.current

    var selectedRegion by remember { mutableStateOf("") }
    var summonerName by remember { mutableStateOf("") }
    var isInputError by remember { mutableStateOf(false) }
    var isSelectRegionEmpty by remember { mutableStateOf(false) }
    var shouldShowFindSummoner by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = state) {
        state.summoner?.let { onContinue(it) }
        state.error?.let { if (it is ErrorType.NotFound) isInputError = true }
    }

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
        scrimColor = Color(0x80242731),
        sheetBackgroundColor = Color(0xFF0E141B),
        sheetContent = {
            RegionsBottomSheet(
                onCloseClick = { coroutineScope.launch { modalSheetState.hide() } },
                onSelectRegion = {
                    coroutineScope.launch { modalSheetState.hide() }
                    isSelectRegionEmpty = false
                    selectedRegion = it
                }
            )
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(360.dp),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.ic_login_header_background),
                contentDescription = "login_header_background"
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
                                350.dp.toPx()
                            }
                        )
                    )
            )

            Image(
                modifier = Modifier
                    .padding(top = 80.dp)
                    .height(168.dp)
                    .width(278.dp)
                    .align(Alignment.TopCenter),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.ic_welcome_pins),
                contentDescription = "login_header_background"
            )
            WelcomeContent(
                selectedRegion = selectedRegion,
                summonerName = summonerName,
                isInputError = isInputError,
                isSelectedRegionEmpty = isSelectRegionEmpty,
                shouldShowFindSummoner = shouldShowFindSummoner,
                onSummonerNameChanged = {
                    if (isInputError) isInputError = false
                    summonerName = it
                },
                onSelectRegionButtonClicked = {
                    coroutineScope.launch {
                        focusManager.clearFocus()
                        keyboardController.hide()
                        modalSheetState.show()
                    }
                },
                onBackClicked = { shouldShowFindSummoner = false },
                onClearClicked = {
                    summonerName = ""
                    isInputError = false
                },
                loginAsSummoner = { shouldShowFindSummoner = true },
                loginAsNonSummoner = { onContinue(null) },
                onContinue = {
                    if (selectedRegion.isNotEmpty())
                        onEvent(LoginEvent.OnLoginClick(selectedRegion, summonerName))
                    else isSelectRegionEmpty = true
                }
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WelcomeContent(
    selectedRegion: String,
    summonerName: String,
    isInputError: Boolean,
    isSelectedRegionEmpty: Boolean,
    shouldShowFindSummoner: Boolean,
    onSummonerNameChanged: (String) -> Unit,
    onSelectRegionButtonClicked: () -> Unit,
    onBackClicked: () -> Unit,
    onClearClicked: () -> Unit,
    loginAsSummoner: () -> Unit,
    loginAsNonSummoner: () -> Unit,
    onContinue: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 300.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WelcomeText()
        Spacer(Modifier.height(50.dp))
        WelcomeUserInputs(
            selectedRegion = selectedRegion,
            summonerName = summonerName,
            isInputError = isInputError,
            isSelectedRegionEmpty = isSelectedRegionEmpty,
            shouldShowFindSummoner = shouldShowFindSummoner,
            loginAsSummoner = { loginAsSummoner() },
            loginAsNonSummoner = { loginAsNonSummoner() },
            onSummonerNameChanged = { onSummonerNameChanged(it) },
            onBackClicked = { onBackClicked() },
            onClearClicked = { onClearClicked() },
            onSelectRegionClicked = { onSelectRegionButtonClicked() },
            onStartClicked = { onContinue() }
        )
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
                borderGradientColors = arrayListOf(0xFFCA9D4B, 0x80242731),
                borderWidth = 0.5.dp
            )
    ) {
        RegionsBottomSheetHeader(
            Modifier
                .fillMaxWidth()
                .height(75.dp),
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
            style = textStyle18(color = 0xFFF6C97F),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
                .align(Alignment.BottomCenter)
        )
    }
    Divider(
        modifier = Modifier.fillMaxWidth(),
        thickness = 0.5.dp,
        color = Color(0x33CA9D4B)
    )
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

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun LoginScreenPreview() {
    LoginContent(
        state = LoginState(),
        onEvent = {},
        onContinue = {}
    )
}