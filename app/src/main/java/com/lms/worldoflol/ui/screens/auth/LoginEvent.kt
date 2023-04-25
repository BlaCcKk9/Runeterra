package com.lms.worldoflol.ui.screens.auth

import com.lms.worldoflol.domain.model.remote.Summoner

sealed class LoginEvent {
    data class OnLoginClick(
        val region: String,
        val summonerName: String,
        var onSuccess: (summoner: Summoner) -> Unit
    ) : LoginEvent()
//    data class OnRegionClick(val region: String) : LoginEvent()
//    object OnSelectRegionClick : LoginEvent()
    object OnRefresh : LoginEvent()
}


