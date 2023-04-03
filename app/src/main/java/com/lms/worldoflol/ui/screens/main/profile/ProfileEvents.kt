package com.lms.worldoflol.ui.screens.main.profile

import com.lms.worldoflol.domain.model.remote.Summoner


sealed class ProfileEvents{
    data class Refresh(val summoner: Summoner): ProfileEvents()
    data class FetchProfile(val summoner: Summoner): ProfileEvents()
}
