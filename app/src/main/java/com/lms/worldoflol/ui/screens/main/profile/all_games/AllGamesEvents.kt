package com.lms.worldoflol.ui.screens.main.profile.all_games

sealed class AllGamesEvents {
    data class GetMatches(
        val region: String,
        val puuid: String,
    ) : AllGamesEvents()
}