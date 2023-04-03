package com.lms.worldoflol.common

import androidx.compose.material.BottomSheetState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
class RuneterraBottomSheet(private val sheetState: BottomSheetState) {

    private lateinit var keyboardController: SoftwareKeyboardController
    private lateinit var focusManager: FocusManager

    @Composable
    fun BottomSheetUpdate(ifExpand: () -> Boolean) {
        keyboardController = LocalSoftwareKeyboardController.current!!
        focusManager = LocalFocusManager.current
        if (ifExpand()) Expand() else Collapse()
    }

    @Composable
    private fun Expand() {
        HideKeyboard()
        LaunchedEffect(key1 = true) {
            sheetState.expand()
        }
    }

    @Composable
    private fun Collapse() {
        LaunchedEffect(key1 = true) {
            sheetState.collapse()
        }
    }

    @Composable
    private fun HideKeyboard(){
        focusManager.clearFocus()
        keyboardController.hide()
    }
}