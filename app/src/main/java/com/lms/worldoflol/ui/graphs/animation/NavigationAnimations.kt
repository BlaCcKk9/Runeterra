package com.lms.worldoflol.ui.graphs.animation

import okhttp3.Route
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry
import com.lms.worldoflol.ui.BottomBarScreen
import com.lms.worldoflol.ui.graphs.Screen
import com.lms.worldoflol.ui.graphs.components.enterChampionAnimation
import com.lms.worldoflol.ui.graphs.components.enterChampionDetailAnimation
import com.lms.worldoflol.ui.graphs.components.enterMatchDetailAnimation
import com.lms.worldoflol.ui.graphs.components.enterProfileDetailAnimation
import com.lms.worldoflol.ui.graphs.components.enterSearchAnimation
import com.lms.worldoflol.ui.graphs.components.exitChampionAnimation
import com.lms.worldoflol.ui.graphs.components.exitChampionDetailAnimation
import com.lms.worldoflol.ui.graphs.components.exitMatchDetailAnimation
import com.lms.worldoflol.ui.graphs.components.exitProfileDetailAnimation
import com.lms.worldoflol.ui.graphs.components.exitSearchAnimation

@OptIn(ExperimentalAnimationApi::class)
fun AnimatedContentScope<NavBackStackEntry>.enterAnimation(route: String, onBackPressed: () -> Boolean) =
    when (route) {
        BottomBarScreen.Profile.route -> enterSlideRight()
        BottomBarScreen.Search.route -> enterSearchAnimation()
        BottomBarScreen.Champions.route -> enterChampionAnimation()
        Screen.PROFILE_DETAIL.route -> enterProfileDetailAnimation { onBackPressed() }
        Screen.CHAMPIN_DETAIl.route -> enterChampionDetailAnimation()
        Screen.MATCH_DETAIL.route -> enterMatchDetailAnimation { onBackPressed() }
        else -> null
    }

@OptIn(ExperimentalAnimationApi::class)
fun AnimatedContentScope<NavBackStackEntry>.exitAnimation(route: String, onBackPressed: () -> Boolean) =
    when (route) {
        BottomBarScreen.Profile.route -> exitSlideLeft()
        BottomBarScreen.Search.route -> exitSearchAnimation()
        BottomBarScreen.Champions.route -> exitChampionAnimation()
        Screen.PROFILE_DETAIL.route -> exitProfileDetailAnimation { onBackPressed() }
        Screen.CHAMPIN_DETAIl.route -> exitChampionDetailAnimation()
        Screen.MATCH_DETAIL.route -> exitMatchDetailAnimation { onBackPressed() }
        else -> null
    }

@OptIn(ExperimentalAnimationApi::class)
fun AnimatedContentScope<NavBackStackEntry>.enterSlideLeft() =
    slideIntoContainer(
        AnimatedContentScope.SlideDirection.Left,
        animationSpec = tween(300)
    )

@OptIn(ExperimentalAnimationApi::class)
fun AnimatedContentScope<NavBackStackEntry>.enterSlideRight() =
    slideIntoContainer(
        AnimatedContentScope.SlideDirection.Right,
        animationSpec = tween(300)
    )

@OptIn(ExperimentalAnimationApi::class)
fun AnimatedContentScope<NavBackStackEntry>.exitSlideLeft() =
    slideOutOfContainer(
        AnimatedContentScope.SlideDirection.Left,
        animationSpec = tween(300)
    )

@OptIn(ExperimentalAnimationApi::class)
fun AnimatedContentScope<NavBackStackEntry>.exitSlideRight() =
    slideOutOfContainer(
        AnimatedContentScope.SlideDirection.Right,
        animationSpec = tween(300)
    )