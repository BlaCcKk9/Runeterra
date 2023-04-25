package com.lms.worldoflol.common

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.lms.worldoflol.utils.backgroundWithBorder

@Composable
fun DropDown(
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.TopStart,
    initiallyOpened: Boolean,
    content: @Composable () -> Unit
) {
    val alpha = animateFloatAsState(
        targetValue = if (initiallyOpened) 1f else 0f,
        animationSpec = tween(
            durationMillis = 300
        )
    )
    val rotateX = animateFloatAsState(
        targetValue = if (initiallyOpened) 0f else -90f,
        animationSpec = tween(
            durationMillis = 300
        )
    )
    Box(
        contentAlignment = alignment,
        modifier = modifier
            .fillMaxWidth()
            .graphicsLayer {
                transformOrigin = TransformOrigin(0.5f, 0f)
                rotationX = rotateX.value
            }
            .alpha(alpha.value)
    ) { content() }
}

@Composable
fun MenuLazyColumn(
    width: Dp,
    content: LazyListScope.() -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .width(width)
            .wrapContentHeight()
            .backgroundWithBorder(
                backgroundColor = 0xFF0E141B,
                borderGradientColors = arrayListOf(0x1ACA9D4B, 0x1AEEE2CC),
                borderWidth = 3.dp
            )
            .padding(start = 20.dp, top = 20.dp),
        horizontalAlignment = Alignment.Start,
        userScrollEnabled = false
    ) { content.invoke(this) }
}

@Composable
fun MenuLazyColumn(
    width: Dp,
    shouldContentCenter: Boolean,
    content: LazyListScope.() -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .width(width)
            .wrapContentHeight()
            .backgroundWithBorder(
                backgroundColor = 0xFF0E141B,
                borderGradientColors = arrayListOf(0x1ACA9D4B, 0x1AEEE2CC),
                borderWidth = 3.dp
            )
            .padding(top = 20.dp),
        horizontalAlignment = Alignment.Start,
        userScrollEnabled = false
    ) { content.invoke(this) }
}

@Composable
fun MenuLazyColumn(content: LazyListScope.() -> Unit) {
    LazyColumn(
        modifier = Modifier
            .wrapContentSize()
            .backgroundWithBorder(
                backgroundColor = 0xFF0E141B,
                borderGradientColors = arrayListOf(0x1ACA9D4B, 0x1AEEE2CC),
                borderWidth = 3.dp
            )
            .padding(start = 20.dp, top = 20.dp, end = 35.dp),
        horizontalAlignment = Alignment.Start,
        userScrollEnabled = false
    ) { content.invoke(this) }
}