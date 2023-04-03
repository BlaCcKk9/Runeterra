package com.lms.worldoflol.ui.screens.main.search

import com.lms.worldoflol.common.ErrorType
import com.lms.worldoflol.common.Region
import com.lms.worldoflol.domain.model.local.SummonerEntity
import com.lms.worldoflol.domain.model.remote.Entry
import com.lms.worldoflol.domain.model.remote.Summoner

data class SearchState(
    var isLoading: Boolean = false,
    val summoner: Summoner? = null,
    val summoners: List<Summoner> = emptyList(),
    val onSearchResult: Boolean = false,
    val showTitle: Boolean = false,
    val error: ErrorType? = null,
    var selectedRegion: String? = null,
    var query: String = ""
)
