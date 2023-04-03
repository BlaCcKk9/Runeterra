package com.lms.worldoflol.ui.graphs.components

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.lms.worldoflol.ui.BottomBarScreen
import com.lms.worldoflol.ui.graphs.Screen
import com.lms.worldoflol.ui.graphs.animation.enterAnimation
import com.lms.worldoflol.ui.graphs.animation.enterSlideLeft
import com.lms.worldoflol.ui.graphs.animation.enterSlideRight
import com.lms.worldoflol.ui.graphs.animation.exitAnimation
import com.lms.worldoflol.ui.graphs.animation.exitSlideLeft
import com.lms.worldoflol.ui.graphs.animation.exitSlideRight

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.animatedComposable(
    route: String,
    onBackPressed: () -> Boolean = { false },
    arguments: List<NamedNavArgument> = emptyList(),
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = route,
        arguments = arguments,
        enterTransition = { enterAnimation(route) { onBackPressed() } },
        exitTransition = { exitAnimation(route) { onBackPressed() } }
    ) { content(it) }
}

// Search Screen Navigation Animation

@OptIn(ExperimentalAnimationApi::class)
fun AnimatedContentScope<NavBackStackEntry>.enterSearchAnimation() =
    when (initialState.destination.route) {
        BottomBarScreen.Profile.route -> enterSlideLeft()
        BottomBarScreen.Champions.route -> enterSlideRight()
        Screen.PROFILE_DETAIL.route -> enterSlideRight()
        else -> null
    }

@OptIn(ExperimentalAnimationApi::class)
fun AnimatedContentScope<NavBackStackEntry>.exitSearchAnimation() =
    when (targetState.destination.route) {
        BottomBarScreen.Profile.route -> exitSlideRight()
        BottomBarScreen.Champions.route -> exitSlideLeft()
        Screen.PROFILE_DETAIL.route -> exitSlideLeft()
        else -> null
    }

// Champions Screen Navigation Animation

@OptIn(ExperimentalAnimationApi::class)
fun AnimatedContentScope<NavBackStackEntry>.enterChampionAnimation() =
    when (initialState.destination.route) {
        Screen.CHAMPIN_DETAIl.route -> enterSlideRight()
        else -> enterSlideLeft()
    }

@OptIn(ExperimentalAnimationApi::class)
fun AnimatedContentScope<NavBackStackEntry>.exitChampionAnimation() =
    when (targetState.destination.route) {
        Screen.CHAMPIN_DETAIl.route -> exitSlideLeft()
        else -> exitSlideRight()
    }

// Profile Detail Screen Navigation Animation

@OptIn(ExperimentalAnimationApi::class)
fun AnimatedContentScope<NavBackStackEntry>.enterProfileDetailAnimation(onBackPressed: () -> Boolean) =
    when (initialState.destination.route) {
        BottomBarScreen.Search.route -> enterSlideLeft()
        Screen.MATCH_DETAIL.route -> if (onBackPressed()) enterSlideRight() else enterSlideLeft()
        else -> null
    }

@OptIn(ExperimentalAnimationApi::class)
fun AnimatedContentScope<NavBackStackEntry>.exitProfileDetailAnimation(onBackPressed: () -> Boolean) =
    when (targetState.destination.route) {
        BottomBarScreen.Search.route -> exitSlideRight()
        Screen.MATCH_DETAIL.route -> if (onBackPressed()) exitSlideRight() else exitSlideLeft()
        else -> null
    }

// Champion Detail Screen Navigation Animation
@OptIn(ExperimentalAnimationApi::class)
fun AnimatedContentScope<NavBackStackEntry>.enterChampionDetailAnimation() =
    when (initialState.destination.route) {
        BottomBarScreen.Champions.route -> enterSlideLeft()
        else -> null
    }

@OptIn(ExperimentalAnimationApi::class)
fun AnimatedContentScope<NavBackStackEntry>.exitChampionDetailAnimation() =
    when (targetState.destination.route) {
        BottomBarScreen.Champions.route -> exitSlideRight()
        else -> null
    }

// Match Detail Screen Navigation Animation
@OptIn(ExperimentalAnimationApi::class)
fun AnimatedContentScope<NavBackStackEntry>.enterMatchDetailAnimation(onBackPressed: () -> Boolean) =
    when (initialState.destination.route) {
        BottomBarScreen.Profile.route -> enterSlideLeft()
        Screen.PROFILE_DETAIL.route -> if (onBackPressed()) enterSlideRight() else enterSlideLeft()
        else -> null
    }

@OptIn(ExperimentalAnimationApi::class)
fun AnimatedContentScope<NavBackStackEntry>.exitMatchDetailAnimation(onBackPressed: () -> Boolean) =
    when (targetState.destination.route) {
        BottomBarScreen.Profile.route -> exitSlideRight()
        Screen.PROFILE_DETAIL.route -> {
            if (onBackPressed()) exitSlideRight() else exitSlideLeft()
        }
        else -> null
    }









