package com.lms.worldoflol.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController

@OptIn(ExperimentalComposeUiApi::class)
fun hideKeyboard(focusManager: FocusManager, keyboardController: SoftwareKeyboardController) {
    focusManager.clearFocus()
    keyboardController.hide()
}