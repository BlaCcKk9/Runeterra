package com.lms.worldoflol.ui.screens.main.search.helper

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.with
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.SoftwareKeyboardController
import com.lms.worldoflol.R

fun getSearchButtonIcon(iconVisible: Boolean, clearVisible: Boolean): Int =
    if (iconVisible) R.drawable.ic_search_text_field
    else if (clearVisible) R.drawable.ic_text_clear
    else R.drawable.ic_arrow_right

fun getRegionTextColor(selectedRegion: String?): Long =
    selectedRegion?.let { 0xB3EEE2CC }
        ?: run { 0x4DEEE2CC }

fun getIconColor(selectedRegion: String?): Long =
    selectedRegion?.let { 0xFFCA9D4B }
        ?: run { 0xB3CA9D4B }


fun getSelectRegionButtonText(selectedRegion: String?): String =
    selectedRegion?.take(7) ?: "Region"

@OptIn(ExperimentalAnimationApi::class)
fun getSearchResultAnimation(targetState: Boolean) =
    if (targetState) scaleIn() with scaleOut()
    else scaleIn() + fadeIn() with scaleOut() + fadeOut()

@Composable
fun getTextfieldColors(): TextFieldColors {
    return TextFieldDefaults.textFieldColors(
        cursorColor = Color(0x66CA9D4B),
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        backgroundColor = Color.Transparent
    )
}

