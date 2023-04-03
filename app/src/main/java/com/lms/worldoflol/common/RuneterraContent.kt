package com.lms.worldoflol.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.lms.worldoflol.ui.screens.main.search.components.NotFoundContent

@Composable
fun RuneterraContent(
    response: Any?,
    isLoading: Boolean,
    error: ErrorType?,
    Skeleton: @Composable () -> Unit,
    onRefresh: () -> Unit = {},
    onBackPressed: () -> Unit = {},
    content: @Composable (BoxScope) -> Unit
) {
    when {
        response != null -> Content(onBackPressed) { content(it) }
        isLoading -> Skeleton()
        error != null -> ErrorContent(error) { onRefresh() }
    }
}

@Composable
fun Content(
    onBackPressed: () -> Unit = {},
    content: @Composable (BoxScope) -> Unit
) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color(0xFF242731))
    ) {
        content(this)
        BackButton { onBackPressed() }
    }
}

@Composable
fun ErrorContent(
    error: ErrorType,
    onRefreshClicked: () -> Unit
) {
    when (error) {
        ErrorType.HTTP -> {}
        ErrorType.NotFound -> {
            NotFoundContent()
        }

        ErrorType.NoInternetConnection -> {
            NoInternetConnectionScreen {
                onRefreshClicked()
            }
        }
    }
}