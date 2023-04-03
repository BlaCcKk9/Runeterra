package com.lms.worldoflol.ui.screens.main.profile

import com.lms.worldoflol.common.ErrorType
import com.lms.worldoflol.domain.model.remote.Entry
import com.lms.worldoflol.domain.model.remote.Match
import com.lms.worldoflol.domain.model.remote.Summoner
import java.io.Serializable


data class ProfileState(
    var isLoading: Boolean = false,
    val entries: List<Entry?>? = null,
    val matches: List<Match?>? = null,
    val isMatchesLoading: Boolean = false,
    val error: ErrorType? = null,
    val summoner: Summoner = Summoner("", "", "", "", 0, 0)
) : Serializable

var firstCall = true

