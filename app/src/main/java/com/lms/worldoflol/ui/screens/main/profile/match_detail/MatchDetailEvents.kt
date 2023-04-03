package com.lms.worldoflol.ui.screens.main.profile.match_detail

import com.lms.worldoflol.ui.screens.main.search.SearchEvents

sealed class MatchDetailEvents {
    data class FetchMatchDetail(
        val region: String,
        val matchId: String,
        val puuid: String
    ) : MatchDetailEvents()
    data class GetSummoner(val summonerName: String, val region: String): MatchDetailEvents()
}