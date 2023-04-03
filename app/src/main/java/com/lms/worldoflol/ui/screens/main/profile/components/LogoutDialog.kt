package com.lms.worldoflol.ui.screens.main.profile.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.lms.worldoflol.R
import com.lms.worldoflol.ui.theme.textStyle16
import com.lms.worldoflol.ui.theme.textStyle18
import com.lms.worldoflol.utils.backgroundWithBorder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

internal const val ANIMATION_TIME = 100L
internal const val DIALOG_BUILD_TIME = 100L

@Composable
fun LogoutDialog(
    onCancel: () -> Unit,
    onLogout: () -> Unit
) {
    AnimatedTransitionDialog(onDismissRequest = onCancel) { animatedTransitionDialogHelper ->
        Surface(
            shape = RoundedCornerShape(15.dp),
            color = Color.Transparent,
            modifier = Modifier
        ) {
            Column(
                modifier = Modifier
                    .padding()
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .backgroundWithBorder(
                        backgroundColor = 0xFF0E141B,
                        borderGradientColors = arrayListOf(0x33CA9D4B, 0x33F6C97F)
                    ),

                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(38.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 50.dp),
                    style = textStyle18(color = 0xB3EEE2CC),
                    text = "Are you sure you want to logout?"
                )
                Spacer(modifier = Modifier.height(20.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_dialog_logout),
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .size(87.dp, 98.dp),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = "are you sure logout image"
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 16.dp
                        )
                        .fillMaxWidth()
                        .wrapContentHeight(),
                ) {
                    ButtonCancel(
                        modifier = Modifier
                            .weight(1f)
                            .height(55.dp)
                            .backgroundWithBorder(
                                backgroundColors = arrayListOf(0x590E141B, 0x99242731),
                                borderGradientColors = 0x66CA9D4B
                            )
                            .clickable {
//                                animatedTransitionDialogHelper::triggerAnimatedDismiss.invoke()
                                onCancel()
                            }
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    ButtonLogout(
                        modifier = Modifier
                            .weight(1f)
                            .height(55.dp)
                            .backgroundWithBorder(
                                backgroundColor = 0xFFCA9D4B,
                                borderGradientColors = arrayListOf(0xFFCA9D4B, 0xFFF6C97F),
                                borderWidth = 0.5.dp
                            )
                            .clickable { onLogout() }
                    )
                }
            }
        }
    }
}

@Composable
fun ButtonCancel(modifier: Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Cancel", style = textStyle16(0xFFEEE2CC))
    }
}

@Composable
fun ButtonLogout(modifier: Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Log Out", style = textStyle16(0xCC0E141B))
    }
}

@Composable
fun AnimatedTransitionDialog(
    onDismissRequest: () -> Unit,
    contentAlignment: Alignment = Alignment.Center,
    content: @Composable (AnimatedTransitionDialogHelper) -> Unit
) {
    val onDismissSharedFlow: MutableSharedFlow<Any> = remember { MutableSharedFlow() }
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val animateTrigger = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        launch {
            delay(DIALOG_BUILD_TIME)
            animateTrigger.value = true
        }
        launch {
            onDismissSharedFlow.asSharedFlow().collectLatest {
                startDismissWithExitAnimation(animateTrigger, onDismissRequest)
            }
        }
    }

    Dialog(
        onDismissRequest = {
            coroutineScope.launch {
                startDismissWithExitAnimation(animateTrigger, onDismissRequest)
            }
        }
    ) {
        Box(
            contentAlignment = contentAlignment,
            modifier = Modifier.fillMaxSize()
        ) {
            AnimatedScaleInTransition(visible = animateTrigger.value) {
                content(AnimatedTransitionDialogHelper(coroutineScope, onDismissSharedFlow))
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun AnimatedScaleInTransition(
    visible: Boolean,
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = scaleIn(),
        exit = scaleOut(),
        content = content
    )
}

class AnimatedTransitionDialogHelper(
    private val coroutineScope: CoroutineScope,
    private val onDismissFlow: MutableSharedFlow<Any>
) {

    fun triggerAnimatedDismiss() {
        coroutineScope.launch {
            onDismissFlow.emit(Any())
        }
    }
}

suspend fun startDismissWithExitAnimation(
    animateTrigger: MutableState<Boolean>,
    onDismissRequest: () -> Unit
) {
    animateTrigger.value = false
    delay(ANIMATION_TIME)
    onDismissRequest()
}