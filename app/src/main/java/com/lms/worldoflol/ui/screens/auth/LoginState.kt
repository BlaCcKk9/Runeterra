package com.lms.worldoflol.ui.screens.auth

import com.lms.worldoflol.common.ErrorType
import com.lms.worldoflol.domain.model.remote.Summoner

data class LoginState(
    val isLoading: Boolean = false,
    val summoner: Summoner? = null,
    val error: ErrorType? = null,
)
