package com.lms.worldoflol.ui.screens.main.profile.all_games

import com.lms.worldoflol.common.ErrorType
import com.lms.worldoflol.domain.model.remote.Entry
import com.lms.worldoflol.domain.model.remote.Match
import com.lms.worldoflol.domain.model.remote.Summoner
import java.io.Serializable

data class AllGamesState(
    var isLoading: Boolean = false,
    var matches: List<Match?>? = null,
    val error: ErrorType? = null,
    var endReached: Boolean = false
) : Serializable