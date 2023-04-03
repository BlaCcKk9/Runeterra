package com.lms.worldoflol.utils

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

fun bottomBarNavigate(
    route: String,
    bottomBarDestination: Boolean,
    scope: CoroutineScope,
    navController: NavHostController,
    wait: Job?,
    onAnimStart: (Job?) -> Unit,
    onAnimCancel: () -> Unit,
) {
    waitEndAnimNavigation(
        scope = scope,
        navController = navController,
        wait = wait,
        onAnimStart = onAnimStart,
        onAnimCancel = onAnimCancel,
    ) {
        navController.navigate(route) {
            if (bottomBarDestination) {
                popUpTo(navController.graph.findStartDestination().id) {
                    inclusive = true
                    saveState = true
                }
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}

fun waitEndAnimNavigation(
    scope: CoroutineScope,
    navController: NavHostController,
    wait: Job?,
    onAnimStart: (Job?) -> Unit,
    onAnimCancel: () -> Unit,
    onNavigate: () -> Unit,
) {
    wait?.cancel()
    if (navController.visibleEntries.value.count() > 1) {
        onAnimStart(scope.launch {
            navController.visibleEntries
                .collect { visibleEntries ->
                    if (visibleEntries.count() == 1) {
                        onNavigate()
                        onAnimCancel()
                        cancel()
                    }
                }
        })
    } else {
        onNavigate()
    }
}

