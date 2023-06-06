package com.lms.worldoflol.ui.screens.auth.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lms.worldoflol.R
import com.lms.worldoflol.common.PrimaryButton
import com.lms.worldoflol.common.SecondaryButton
import com.lms.worldoflol.common.SelectRegionButton
import com.lms.worldoflol.ui.screens.main.search.helper.getTextfieldColors
import com.lms.worldoflol.ui.theme.QuadrataFamily
import com.lms.worldoflol.ui.theme.textStyle
import com.lms.worldoflol.utils.backgroundWithBorder

@OptIn(ExperimentalAnimationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WelcomeUserInputs(
    selectedRegion: String,
    summonerName: String = "",
    isInputError: Boolean = false,
    isSelectedRegionEmpty: Boolean = false,
    shouldShowFindSummoner: Boolean,
    loginAsSummoner: () -> Unit,
    loginAsNonSummoner: () -> Unit,
    onSummonerNameChanged: (String) -> Unit,
    onBackClicked: () -> Unit,
    onClearClicked: () -> Unit,
    onSelectRegionClicked: () -> Unit,
    onStartClicked: () -> Unit,
) {
    AnimatedContent(shouldShowFindSummoner) {
        Column(Modifier.fillMaxWidth()) {
            if (it)
                FindSummonerContent(
                    selectedRegion = selectedRegion,
                    summonerName = summonerName,
                    isInputError = isInputError,
                    isSelectedRegionEmpty = isSelectedRegionEmpty,
                    onSummonerNameChanged = { onSummonerNameChanged(it) },
                    onBackClicked = { onBackClicked() },
                    onClearClicked = { onClearClicked() },
                    onSelectRegionButtonClicked = { onSelectRegionClicked() },
                    onStartClicked = { onStartClicked() }
                )
            else
                SummonerOptionsContent(
                    loginAsSummoner = { loginAsSummoner() },
                    loginAsNonSummoner = { loginAsNonSummoner() }
                )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SummonerOptionsContent(
    loginAsSummoner: () -> Unit = {},
    loginAsNonSummoner: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        PrimaryButton(
            text = "I am Summoner",
            modifier = Modifier.fillMaxWidth(),
            onClick = { loginAsSummoner() }
        )
        SecondaryButton(
            text = "I am not Summoner",
            modifier = Modifier.fillMaxWidth(),
            onClick = { loginAsNonSummoner() }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FindSummonerContent(
    selectedRegion: String = "",
    summonerName: String = "",
    isInputError: Boolean = false,
    isSelectedRegionEmpty: Boolean = false,
    onSummonerNameChanged: (String) -> Unit = {},
    onSelectRegionButtonClicked: () -> Unit = {},
    onBackClicked: () -> Unit = {},
    onClearClicked: () -> Unit = {},
    onStartClicked: () -> Unit = {}
) {
    var isButtonActive by remember {
        mutableStateOf(false)
    }
    SelectRegionButton(
        regionName = selectedRegion,
        isSelectedRegionEmpty = isSelectedRegionEmpty,
        onClick = { onSelectRegionButtonClicked() }
    )
    Spacer(modifier = Modifier.height(6.dp))
    SummonerNameInput(
        summonerName = summonerName,
        isError = isInputError,
        isSelectedRegionEmpty = isSelectedRegionEmpty,
        onClearClicked = { onClearClicked() },
        onTextChanged = {
            isButtonActive = it.isNotEmpty()
            onSummonerNameChanged(it)
        }
    )
    Spacer(modifier = Modifier.height(40.dp))
    PrimaryButton(
        text = "Start",
        enable = isButtonActive,
        onClick = { onStartClicked() },
        modifier = Modifier.fillMaxWidth(),
    )
    Text(
        text = "I am not Summoner",
        style = textStyle(
            size = 16.sp,
            color = 0xFFCA9D4B
        ),
        modifier = Modifier
            .padding(30.dp)
            .fillMaxWidth()
            .height(60.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() })
            { onBackClicked() }
    )
}

@Composable
fun SummonerNameInput(
    modifier: Modifier = Modifier,
    summonerName: String = "",
    isError: Boolean = false,
    isSelectedRegionEmpty: Boolean = false,
    onClearClicked: () -> Unit,
    onTextChanged: (String) -> Unit
) {
    val borderColor: Long = if (isError) 0x80F12242 else 0x4DCA9D4B
    Column {
        Box(
            modifier = modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(60.dp)
                .backgroundWithBorder(
                    backgroundColor = 0xCC0E141B,
                    borderColor = borderColor
                )
                .padding(start = 9.dp, end = 20.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            if (isError)
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_text_clear),
                    tint = Color(0x80F12242L),
                    contentDescription = "error_icon",
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .clickable { onClearClicked() }
                )

            TextField(
                value = summonerName,
                onValueChange = { onTextChanged(it) },
                placeholder = { SummonerNameHintt() },
                singleLine = true,
                textStyle = textStyle(color = 0xFFEEE2CC, textAlign = TextAlign.Start),
                colors = getTextfieldColors(),
            )
        }
        WelcomeErrorText(
            isInputError = isError,
            isSelectedRegionEmpty = isSelectedRegionEmpty
        )
    }
}

@Composable
fun WelcomeErrorText(
    isInputError: Boolean,
    isSelectedRegionEmpty: Boolean
) {
    val isWarningVisible = isInputError || isSelectedRegionEmpty
    val warningText = when {
        isInputError -> "Summoner name don't match"
        isSelectedRegionEmpty -> "Region must be selected"
        else -> ""
    }
    AnimatedVisibility(
        visible = isWarningVisible,
        enter = slideInVertically(),
        exit = slideOutVertically()
    ) {
        Row(Modifier.padding(start = 24.dp, top = 12.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.ic_warning),
                tint = Color(0xB3F12242),
                contentDescription = "warning",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = warningText,
                style = textStyle(
                    size = 12.sp,
                    color = 0xB3F12242
                )
            )
        }
    }
}

@Composable
fun SummonerNameHintt() {
    Text(
        text = "Summoner Name",
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Start,
        fontFamily = QuadrataFamily,
        fontWeight = FontWeight(400),
        fontSize = 16.sp,
        color = Color(0x80EEE2CC)
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun FindSummonerPreview() {
    WelcomeUserInputs(
        selectedRegion = "",
        summonerName = "Summoner Name",
        shouldShowFindSummoner = false,
        isInputError = true,
        loginAsSummoner = { },
        loginAsNonSummoner = { },
        onSummonerNameChanged = { },
        onBackClicked = { },
        onClearClicked = { },
        onSelectRegionClicked = { },
        onStartClicked = { }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun SummonerOptionsContentPreview() {
    WelcomeUserInputs(
        selectedRegion = "",
        summonerName = "Summoner Name",
        shouldShowFindSummoner = true,
        loginAsSummoner = { },
        loginAsNonSummoner = { },
        onSummonerNameChanged = { },
        onBackClicked = { },
        onClearClicked = { },
        onSelectRegionClicked = { },
        onStartClicked = { }
    )
}