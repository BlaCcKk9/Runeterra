package com.lms.worldoflol.ui.screens.main.champions.champion_detail

import com.lms.worldoflol.common.ErrorType
import com.lms.worldoflol.domain.model.remote.Champion
import com.lms.worldoflol.domain.model.remote.ChampionDetail

data class ChampionDetailsState (
    val isLoading: Boolean = false,
    val championDetail: ChampionDetail? = null,
    val error: ErrorType? = null
)