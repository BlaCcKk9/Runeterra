package com.lms.worldoflol.ui.graphs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.lms.worldoflol.domain.model.remote.Summoner
import com.lms.worldoflol.ui.BottomBarScreen
import com.lms.worldoflol.ui.graphs.animation.enterSlideLeft
import com.lms.worldoflol.ui.graphs.animation.exitSlideRight
import com.lms.worldoflol.ui.graphs.components.animatedComposable
import com.lms.worldoflol.ui.graphs.components.navigate
import com.lms.worldoflol.ui.screens.main.profile.ProfileScreen
import com.lms.worldoflol.ui.screens.main.search.SearchScreen
import com.lms.worldoflol.ui.screens.main.champions.ChampionsScreen
import com.lms.worldoflol.ui.screens.main.champions.champion_detail.ChampionDetailsScreen
import com.lms.worldoflol.ui.screens.main.profile.all_games.AllGamesScreen
import com.lms.worldoflol.ui.screens.main.profile.match_detail.MatchDetailScreen
import com.lms.worldoflol.ui.screens.main.search.profile_detail.ProfileDetailScreen
import com.lms.worldoflol.utils.Moshi

@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainNavGraph(
    navHostController: NavHostController,
    summoner: Summoner?,
    bottomBarVisibility: (isVisible: Boolean) -> Unit
) {

    val moshi = Moshi(Summoner::class.java)
    var backPressed by remember {
        mutableStateOf(false)
    }
    val onBackPressed by remember {
        derivedStateOf {
            backPressed
        }
    }

    AnimatedNavHost(
        navController = navHostController,
        route = Graph.MAIN,
        startDestination = BottomBarScreen.Profile.route,
        enterTransition = { enterSlideLeft() },
        exitTransition = { exitSlideRight() }
    ) {
        animatedComposable(BottomBarScreen.Profile.route) {
            ProfileScreen(
                summoner = summoner,
                bottomBarVisibility = { bottomBarVisibility(it) },
                navigateToMatchDetail = {
                    navigate(
                        navHostController = navHostController,
                        route = "match-detail/${it.summonerRegion}/${it.matchId}/${it.summonerPuuid}"
                    )
                },
                navigateToAllGames = { region, puuid ->
                    navigate(
                        navHostController = navHostController,
                        route ="all-games/${region}/${puuid}"
                    )
                }
            )
        }

        animatedComposable(
            route = Screen.PROFILE_DETAIL.route,
            onBackPressed = { onBackPressed }
        ) { entry ->
            val summonerJson = entry.arguments?.getString("profile")
            val summoner = moshi.fromJson(summonerJson)
            bottomBarVisibility(false)
            ProfileDetailScreen(
                summoner = summoner,
                onBackPressed = {
                    backPressed = true
                    bottomBarVisibility(true)
                    navHostController.navigateUp()
                }
            ) {
                backPressed = false
                navigate(
                    route = "match-detail/${it.summonerRegion}/${it.matchId}/${it.summonerPuuid}",
                    navHostController = navHostController
                )
            }
        }

        animatedComposable(
            route = Screen.MATCH_DETAIL.route,
            arguments = Screen.MATCH_DETAIL.arguments,
            onBackPressed = { onBackPressed }
        ) {
            bottomBarVisibility(false)
            val region = it.arguments?.getString("region") ?: ""
            val matchId = it.arguments?.getString("matchId") ?: ""
            val puuid = it.arguments?.getString("puuid") ?: ""

            MatchDetailScreen(
                region = region,
                matchId = matchId,
                puuid = puuid,
                onBackPressed = {
                    backPressed = true
                    navHostController.navigateUp()
                }
            ) {
                backPressed = false
                navigate(
                    route = Screen.PROFILE_DETAIL
                        .route
                        .replace("{profile}", moshi.toJson(it)),
                    navHostController = navHostController
                )
            }
        }


        animatedComposable(route = BottomBarScreen.Search.route) {
            bottomBarVisibility(true)
            SearchScreen {
                navigate(
                    navHostController = navHostController,
                    route = Screen.PROFILE_DETAIL.route.replace("{profile}", moshi.toJson(it))
                )
            }
        }

        animatedComposable(BottomBarScreen.Champions.route) {
            bottomBarVisibility(true)
            ChampionsScreen {
                navigate(
                    navHostController = navHostController,
                    route = Screen.CHAMPIN_DETAIl.route.replace("{championName}", it)
                )
            }
        }

        animatedComposable(route = Screen.CHAMPIN_DETAIl.route) {
            val championName = it.arguments?.getString("championName")
            bottomBarVisibility(false)
            ChampionDetailsScreen(
                navController = navHostController,
                championName = championName ?: ""
            )
        }

        animatedComposable(
            route = Screen.ALL_GAMES.route,
            arguments = Screen.ALL_GAMES.arguments
        ) {
            bottomBarVisibility(false)
            val region = it.arguments?.getString("region") ?: ""
            val puuid = it.arguments?.getString("puuid") ?: ""
            AllGamesScreen(region = region, puuid = puuid)
        }
    }
}

