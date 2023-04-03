package com.lms.worldoflol.ui.screens.main.search

import com.lms.worldoflol.domain.model.local.SummonerEntity
import com.lms.worldoflol.domain.model.remote.Summoner

sealed class SearchEvents{
    data class DeleteSummoner(val summoner: Summoner): SearchEvents()
    object DeleteAll: SearchEvents()
    data class GetSummoner(val summonerName: String, val region: String): SearchEvents()
    data class GetRecentSearches(var name: String): SearchEvents()
}
