package com.lms.worldoflol.ui.screens.main.search.profile_detail.components

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.with
import androidx.compose.runtime.Composable
import com.lms.worldoflol.ui.screens.main.search.profile_detail.ProfileDetailState


@OptIn(ExperimentalAnimationApi::class)
fun profileAnimations(state: ProfileDetailState): ContentTransform =
    when {
        state.entries != null -> {
            fadeIn() with fadeOut()
        }
        else -> {
            scaleIn(animationSpec = spring(stiffness = Spring.StiffnessLow)) with scaleOut()
        }
    }
