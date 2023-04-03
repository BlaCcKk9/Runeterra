package com.lms.worldoflol.ui.screens.main.profile.match_detail

import com.lms.worldoflol.common.ErrorType
import com.lms.worldoflol.domain.model.local.MatchDetail
import com.lms.worldoflol.domain.model.remote.Summoner
import java.io.Serializable

data class MatchDetailState(
    var isLoading: Boolean = true,
    val matchDetail: MatchDetail? = null,
    val error: ErrorType? = null,
    var summoner: Summoner? = null,
) : Serializable