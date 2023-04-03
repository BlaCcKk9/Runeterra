package com.lms.worldoflol.ui.screens.main.search.profile_detail

import com.lms.worldoflol.domain.model.remote.Summoner


sealed class ProfileDetailEvent{
    data class FetchProfile(val summoner: Summoner): ProfileDetailEvent()
    data class Refresh(val summoner: Summoner): ProfileDetailEvent()
}