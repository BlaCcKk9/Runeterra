package com.lms.worldoflol.ui.graphs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.lms.worldoflol.domain.model.remote.Summoner
import com.lms.worldoflol.ui.screens.main.MainScreen
import com.lms.worldoflol.utils.Moshi

@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RootNavigationGraph(navController: NavHostController) {
    val moshi = Moshi(Summoner::class.java)
    AnimatedNavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION,
        modifier = Modifier.background(Color(0xFF242731))
    ) {
        authNavGraph(navController)
        composable(route = Graph.MAIN) {
            val summonerJson = it.arguments?.getString("profile")
            val summoner = moshi.fromJson(summonerJson!!)
            MainScreen(summoner = summoner)
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val MAIN = "main_graph/profile={profile}"
}


