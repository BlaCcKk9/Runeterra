package com.lms.worldoflol.ui.graphs

import androidx.navigation.NavType
import androidx.navigation.navArgument
import okhttp3.Route

sealed class Screen(val route: String) {
    object PROFILE_DETAIL : Screen("$profile_detail/profile={profile}")
    object CHAMPIN_DETAIl : Screen("$champion_detail/championName={championName}")
    object MATCH_DETAIL : Screen("$match_detail/{region}/{matchId}/{puuid}") {
        val arguments = listOf(
            navArgument("region") { type = NavType.StringType },
            navArgument("matchId") { type = NavType.StringType },
            navArgument("puuid") { type = NavType.StringType }
        )
    }
    object ALL_GAMES : Screen("$all_games/{region}/{puuid}") {
        val arguments = listOf(
            navArgument("region") { type = NavType.StringType },
            navArgument("puuid") { type = NavType.StringType }
        )
    }
}

const val profile_detail = "profile-details"
const val champion_detail = "champion-detail"
const val match_detail = "match-detail"
const val all_games = "all-games"