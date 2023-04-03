package com.lms.worldoflol.ui.screens.main.search.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.TextField
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lms.worldoflol.R
import com.lms.worldoflol.ui.screens.main.search.helper.getIconColor
import com.lms.worldoflol.ui.screens.main.search.helper.getRegionTextColor
import com.lms.worldoflol.ui.screens.main.search.helper.getSearchButtonIcon
import com.lms.worldoflol.ui.screens.main.search.helper.getSelectRegionButtonText
import com.lms.worldoflol.ui.screens.main.search.helper.getTextfieldColors
import com.lms.worldoflol.ui.theme.textStyle14
import com.lms.worldoflol.utils.hideKeyboard
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    scope: CoroutineScope = rememberCoroutineScope(),
    keyboardController: SoftwareKeyboardController = LocalSoftwareKeyboardController.current!!,
    focusManager: FocusManager = LocalFocusManager.current,
    region: String?,
    onSummonerNameChanged: (summonerName: TextFieldValue) -> Unit,
    onSearchClick: (region: String, summonerName: String) -> Unit
) {
    var menuVisible by remember { mutableStateOf(false) }
    var warningVisible by remember { mutableStateOf(false) }
    var selectedRegion by remember { mutableStateOf(region) }

    Column {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RegionsMenuButton(
                selectedRegion = selectedRegion,
                menuVisible = menuVisible,
                onClick = { menuVisible = it }
            )

            SummonerNameTextField(
                onSearchClick = { summonerName ->
                    hideKeyboard(focusManager, keyboardController)
                    selectedRegion?.let { onSearchClick(it, summonerName) } ?: run {
                        scope.launch {
                            warningVisible = true
                            delay(3000)
                            warningVisible = false
                        }
                    }
                },
                onSummonerNameChanged = { onSummonerNameChanged(it) }
            )
        }
        EmptyRegionWarning(
            visible = warningVisible,
            warningText = "Region must be selected",
            warningIcon = R.drawable.ic_warning,
            modifier = Modifier.padding(top = 10.dp)
        )
        RegionsMenu(
            modifier = Modifier.padding(top = 5.dp),
            visible = menuVisible
        ) {
            menuVisible = false
            selectedRegion = it
        }
    }
}


@Composable
fun RegionsMenuButton(
    selectedRegion: String?,
    menuVisible: Boolean,
    onClick: (expand: Boolean) -> Unit
) {

    val iconAngle by animateFloatAsState(
        targetValue = if (menuVisible) -180f else 0f,
        animationSpec = tween(durationMillis = 100, easing = FastOutLinearInEasing)
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onClick(!menuVisible) }
    ) {
        Text(
            text = getSelectRegionButtonText(selectedRegion),
            style = textStyle14(getRegionTextColor(selectedRegion))
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_down),
            tint = Color(getIconColor(selectedRegion)),
            contentDescription = "",
            modifier = Modifier
                .padding(start = 10.dp)
                .rotate(iconAngle),
        )

        Divider(
            color = Color(0x26CA9D4B),
            modifier = Modifier
                .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
                .width(1.dp)
                .fillMaxHeight(),
        )
    }

}

@Composable
fun SummonerNameTextField(
    onSearchClick: (summonerName: String) -> Unit,
    onSummonerNameChanged: (summonerName: TextFieldValue) -> Unit,
) {
    var summonerName by remember { mutableStateOf(TextFieldValue()) }
    var clearVisible by remember { mutableStateOf(false) }

    Box(Modifier.fillMaxWidth()) {

        TrailingButtonSection(
            modifier = Modifier.align(Alignment.CenterEnd),
            iconVisible = summonerName.text.isBlank(),
            clearVisible = clearVisible,
            onSearchClick = {
                onSearchClick(summonerName.text)
                clearVisible = true
            },
            onClearClick = {
                summonerName = TextFieldValue()
                onSummonerNameChanged(summonerName)
                clearVisible = false
            }
        )

        TextField(
            value = summonerName,
            onValueChange = {
                summonerName = it
                onSummonerNameChanged(it)
                clearVisible = false
            },
            singleLine = true,
            textStyle = textStyle14(0x99EEE2CC, textAlign = TextAlign.Start),
            placeholder = { SummonerNameHint() },
            colors = getTextfieldColors(),
            modifier = Modifier.padding(end = 30.dp)
        )

    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TrailingButtonSection(
    modifier: Modifier = Modifier,
    iconVisible: Boolean = false,
    clearVisible: Boolean,
    onSearchClick: () -> Unit,
    onClearClick: () -> Unit
) {

    AnimatedContent(
        targetState = iconVisible,
        transitionSpec = { fadeIn() with fadeOut() },
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = getSearchButtonIcon(it, clearVisible)),
            tint = Color(0xFFCA9D4B),
            contentDescription = "",
            modifier = Modifier.clickable(!it) {
                if (clearVisible) onClearClick() else onSearchClick()
            }
        )
    }
}


@Composable
fun SummonerNameHint() {
    Text(
        text = "Summoner Name",
        style = textStyle14(color = 0x4DEEE2CC, textAlign = TextAlign.Start),
        modifier = Modifier.fillMaxWidth(),
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun SearchTextFieldPreview() {
    Box {
        SearchTextField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(65.dp),
            region = null,
            onSearchClick = { _, _ -> },
            onSummonerNameChanged = {}
        )
    }

}