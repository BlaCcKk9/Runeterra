package com.lms.worldoflol.ui.screens.main.search.profile_detail

import com.lms.worldoflol.common.ErrorType
import com.lms.worldoflol.domain.model.remote.Entry
import com.lms.worldoflol.domain.model.remote.Match
import com.lms.worldoflol.domain.model.remote.Summoner
import java.io.Serializable

data class ProfileDetailState (
    var isLoading: Boolean = true,
    val entries: List<Entry?>? = null,
    val matches: List<Match?>? = null,
    val isMatchesLoading: Boolean = false,
    val isEntnriesLoading: Boolean = false,
    val error: ErrorType? = null,
    val summoner: Summoner? = null
)

