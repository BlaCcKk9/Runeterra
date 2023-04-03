package com.lms.worldoflol.ui.screens.main.champions.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.TextField
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lms.worldoflol.R
import com.lms.worldoflol.ui.screens.main.search.helper.getTextfieldColors
import com.lms.worldoflol.ui.theme.textStyle14


@Composable
fun ChampionsSearchTextField(
    modifier: Modifier = Modifier,
    query: String = "",
    onChampionsNameChange: (championName: TextFieldValue) -> Unit,
    onFilterClicked: (Boolean) -> Unit
) {
    var isFiltersVisible by remember { mutableStateOf(false) }
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        FilterIcon(
            active = isFiltersVisible,
            modifier = Modifier
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null
                ) {
                    isFiltersVisible = !isFiltersVisible
                    onFilterClicked(isFiltersVisible)
                },
        )

        SummonerNameTextField(
            query = query,
            onSummonerNameChanged = { onChampionsNameChange(it) }
        )
    }
}

@Composable
fun SummonerNameTextField(
    query: String = "",
    onSummonerNameChanged: (summonerName: TextFieldValue) -> Unit,
) {
    var championsName by remember { mutableStateOf(TextFieldValue(query)) }
    var clearTextVisible by remember { mutableStateOf(false) }

    Box(Modifier.fillMaxWidth()) {
        ClearTextIconButton(
            modifier = Modifier.align(Alignment.CenterEnd),
            clearTextVisible = clearTextVisible,
            onClearClick = {
                championsName = TextFieldValue()
                onSummonerNameChanged(championsName)
                clearTextVisible = false
            }
        )

        TextField(
            value = championsName,
            singleLine = true,
            textStyle = textStyle14(color = 0x99EEE2CC, textAlign = TextAlign.Start),
            placeholder = { ChampionNameHint() },
            colors = getTextfieldColors(),
            modifier = Modifier.padding(end = 30.dp),
            onValueChange = {
                championsName = it
                onSummonerNameChanged(it)
                clearTextVisible = it.text.isNotEmpty()
            },
        )

    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ClearTextIconButton(
    modifier: Modifier = Modifier,
    clearTextVisible: Boolean,
    onClearClick: () -> Unit
) {
    val searchIcon = R.drawable.ic_search_text_field
    val clearTextIcon = R.drawable.ic_text_clear
    AnimatedContent(
        targetState = clearTextVisible,
        transitionSpec = { fadeIn() with fadeOut() },
        modifier = modifier
    ) {
        if (clearTextVisible) Image(
            painter = painterResource(id = clearTextIcon),
            contentDescription = "",
            modifier = Modifier.clickable { onClearClick() }
        )
        else Image(
            painter = painterResource(id = searchIcon),
            contentDescription = "",
        )

    }
}

@Composable
fun FilterIcon(
    modifier: Modifier = Modifier,
    active: Boolean,
) {
    val activedIcon = R.drawable.ic_champions_filter_active
    val disabledIcon = R.drawable.ic_champions_filter_disable
    val filterIcon  = if (active) activedIcon else disabledIcon

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Image(
            modifier = Modifier.size(30.dp),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = filterIcon),
            contentDescription = "",
        )
        Divider(
            color = Color(0x26CA9D4B),
            modifier = Modifier
                .padding(start = 16.dp, top = 10.dp, bottom = 10.dp)
                .width(1.dp)
                .fillMaxHeight()
        )
    }
}


@Composable
fun ChampionNameHint() {
    Text(
        text = "Search Champion",
        style = textStyle14(color = 0x4DEEE2CC, textAlign = TextAlign.Start),
        modifier = Modifier.fillMaxWidth(),
    )
}

@Preview
@Composable
fun SearchTextFieldPreview() {
    Box {
        ChampionsSearchTextField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(65.dp),
            onFilterClicked = { _ -> },
            onChampionsNameChange = {}
        )
    }
}