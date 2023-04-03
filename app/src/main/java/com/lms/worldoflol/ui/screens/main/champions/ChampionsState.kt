package com.lms.worldoflol.ui.screens.main.champions

import com.lms.worldoflol.common.ErrorType
import com.lms.worldoflol.domain.model.remote.Champion

data class ChampionsState (
    val isLoading: Boolean = false,
    val champions: List<Champion> = emptyList(),
    val error: ErrorType? = null,
    var championSearchQuery: String = "",
    var selectedRole: String = "",
    var selectedDifficulty: String = ""
)
