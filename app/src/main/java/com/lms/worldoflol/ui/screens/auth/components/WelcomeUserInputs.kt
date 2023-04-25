package com.lms.worldoflol.ui.screens.auth.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lms.worldoflol.common.PrimaryButton
import com.lms.worldoflol.common.SecondaryButton
import com.lms.worldoflol.common.SelectRegionButton
import com.lms.worldoflol.ui.screens.main.search.helper.getTextfieldColors
import com.lms.worldoflol.ui.theme.QuadrataFamily
import com.lms.worldoflol.ui.theme.textFieldHintColor
import com.lms.worldoflol.ui.theme.textStyle
import com.lms.worldoflol.ui.theme.textStyle14
import com.lms.worldoflol.utils.backgroundWithBorder

@Composable
fun WelcomeUserInputs(
    selectedRegion: String,
    summonerName: String = "Miyvarxaaaar",
    isInputError: Boolean = false,
    shouldShowFindSummoner: Boolean,
    loginAsSummoner: () -> Unit,
    loginAsNonSummoner: () -> Unit,
    onSummonerNameChanged: (String) -> Unit,
    onClearClicked: () -> Unit,
    onSelectRegionClicked: () -> Unit,
    onStartClicked: () -> Unit,
) {
    Column(Modifier.fillMaxWidth()) {
        if (shouldShowFindSummoner)
            FindSummonerContent(
                selectedRegion = selectedRegion,
                summonerName = summonerName,
                isInputError = isInputError,
                onSummonerNameChanged = { onSummonerNameChanged(it) },
                onClearClicked = { onClearClicked },
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

@Composable
fun FindSummonerContent(
    selectedRegion: String = "",
    summonerName: String = "",
    isInputError: Boolean = false,
    onSummonerNameChanged: (String) -> Unit = {},
    onSelectRegionButtonClicked: () -> Unit = {},
    onClearClicked: () -> Unit = {},
    onStartClicked: () -> Unit = {}
) {
    SelectRegionButton(
        regionName = selectedRegion,
        onClick = { onSelectRegionButtonClicked() }
    )
    Spacer(modifier = Modifier.height(6.dp))
    SummonerNameInput(
        summonerName = summonerName,
        isError = isInputError,
        onClearClicked = { onClearClicked() },
        onTextChanged = { onSummonerNameChanged(it) }
    )
    Spacer(modifier = Modifier.height(40.dp))
    PrimaryButton(
        text = "Start",
        modifier = Modifier.fillMaxWidth(),
        onClick = { onStartClicked() }
    )
}

@Composable
fun SummonerNameInput(
    modifier: Modifier = Modifier,
    summonerName: String = "",
    isError: Boolean = false,
    onClearClicked: () -> Unit,
    onTextChanged: (String) -> Unit
) {
    val borderColor: Long = if (isError) 0x80F12242 else 0x4DCA9D4B
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
                imageVector = ImageVector.vectorResource(id = com.lms.worldoflol.R.drawable.ic_text_clear),
                tint = Color(0x80F12242L),
                contentDescription = "error_icon",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable { onClearClicked() }
            )
        TextField(
            value = summonerName,
            onValueChange = { onTextChanged(it) },
            singleLine = true,
            textStyle = textStyle(color = 0xFFEEE2CC, textAlign = TextAlign.Start),
            colors = getTextfieldColors(),
        )
    }
}

@Composable
fun SummonerNameHintt() {
    Text(
        text = "Summoner Name",
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 23.dp),
        textAlign = TextAlign.Start,
        fontFamily = QuadrataFamily,
        fontWeight = FontWeight(400),
        fontSize = 18.sp,
        color = textFieldHintColor
    )
}

@Preview
@Composable
fun FindSummonerPreview() {
    WelcomeUserInputs(
        selectedRegion = "",
        summonerName = "Summoner Name",
        shouldShowFindSummoner = false,
        isInputError = true,
        loginAsSummoner = {  },
        loginAsNonSummoner = {  },
        onSummonerNameChanged = {  },
        onClearClicked = {  },
        onSelectRegionClicked = {  }) {
    }
}

@Preview
@Composable
fun SummonerOptionsContentPreview() {
    WelcomeUserInputs(
        selectedRegion = "",
        summonerName = "Summoner Name",
        shouldShowFindSummoner = true,
        loginAsSummoner = {  },
        loginAsNonSummoner = {  },
        onSummonerNameChanged = {  },
        onClearClicked = {  },
        onSelectRegionClicked = {  }) {
    }
}