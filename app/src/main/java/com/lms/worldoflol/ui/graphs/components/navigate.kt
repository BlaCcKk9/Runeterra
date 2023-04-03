package com.lms.worldoflol.ui.graphs.components

import androidx.navigation.NavHostController

fun navigate(
    navHostController: NavHostController,
    route: String
){
    navHostController.navigate(route) {
        popUpTo(route) {
            saveState = true
            inclusive = true
        }
        launchSingleTop = true
        restoreState = true
    }
}