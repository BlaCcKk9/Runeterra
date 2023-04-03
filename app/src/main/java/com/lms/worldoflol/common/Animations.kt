package com.lms.worldoflol.common


import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

val slideInHorizontally = slideInHorizontally(
    initialOffsetX = { 500 },
    animationSpec = tween(durationMillis = 500)
)

val slideOutHorizontally = slideOutHorizontally(
    targetOffsetX = { 0 },
    animationSpec = tween(durationMillis = 500)
)

sealed class Animations(val enter: EnterTransition, val exit: ExitTransition) {
    object SlideScreen : Animations(slideInHorizontally, slideOutHorizontally)
    object SlideMenu : Animations(slideInHorizontally(initialOffsetX = { it }) + fadeIn(),
        slideOutHorizontally(targetOffsetX = { it }) + fadeOut()
    )
}
