package com.lms.worldoflol.ui.graphs

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.lms.worldoflol.domain.model.remote.Summoner
import com.lms.worldoflol.ui.screens.auth.LoginScreen
import com.lms.worldoflol.utils.Moshi

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.authNavGraph(navController: NavController) {

    val moshi = Moshi(Summoner::class.java)

    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route,
    ) {
        composable(route = AuthScreen.Login.route) {
            LoginScreen { summoner ->
                navController.popBackStack()
                navController.navigate(Graph.MAIN.replace("{profile}", moshi.toJson(summoner)))
            }
        }
    }
}

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "LOGIN")
}