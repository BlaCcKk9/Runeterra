package com.lms.worldoflol.ui.screens.main.search.profile_detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lms.worldoflol.common.Resource
import com.lms.worldoflol.common.getBaseUrl
import com.lms.worldoflol.common.getEntriesUrl
import com.lms.worldoflol.di.AppModule_ProvideSummonerDatabaseFactory
import com.lms.worldoflol.domain.model.remote.Summoner
import com.lms.worldoflol.domain.use_case.get_entries.GetEntriesUseCase
import com.lms.worldoflol.domain.use_case.get_matches.GetMatchesUseCase
import com.lms.worldoflol.domain.use_case.summoner_entitiy.SummonersUseCases
import com.lms.worldoflol.ui.screens.main.profile.ProfileEvents
import com.lms.worldoflol.ui.screens.main.profile.ProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileDetailViewModel @Inject constructor(
    private val getEentriesUseCase: GetEntriesUseCase,
    private val summonersUseCases: SummonersUseCases,
    private val getMatchesUseCase: GetMatchesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileDetailState())
    val state: StateFlow<ProfileDetailState>
        get() = _state.stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            ProfileDetailState(
                isEntnriesLoading = true,
                isMatchesLoading = true,
                summoner = null
            )
        )


    @RequiresApi(Build.VERSION_CODES.S)
    fun onEvent(event: ProfileDetailEvent) {
        when (event) {
            is ProfileDetailEvent.FetchProfile -> {
                getEntries(event.summoner)
                getMatches(event.summoner.region, event.summoner.puuid)
            }

            is ProfileDetailEvent.Refresh -> {
                _state.update { it.copy(entries = null, isLoading = true, error = null) }
                getEntries(event.summoner)
                getMatches(event.summoner.region, event.summoner.puuid)
            }
        }
    }

    private fun getEntries(summoner: Summoner) {
        val url = getEntriesUrl(getBaseUrl(summoner.region), summoner.id)
        fetchEntries(url, summoner)
    }


    private fun getMatches(region: String, puuid: String) {
        viewModelScope.launch {
            getMatchesUseCase(region = region, puuid = puuid).collect { match ->
                when (match) {
                    is Resource.Success -> {
                        _state.update { it.copy(matches = match.data, isMatchesLoading = false) }
                    }
                    is Resource.Error -> {
                        _state.update { it.copy(error = match.error) }
                    }
                    is Resource.Loading -> {
                        _state.update { it.copy(isMatchesLoading = true) }
                    }
                }
            }
        }
    }

    private fun fetchEntries(url: String, summoner: Summoner) {
        viewModelScope.launch {
            getEentriesUseCase(url).collect { entry ->
                when (entry) {
                    is Resource.Success -> {
                        saveSummoner(summoner)
                        _state.update { it.copy(entries = entry.data, isEntnriesLoading = false, summoner = summoner) }
                    }
                    is Resource.Error -> {
                        _state.update { it.copy(error = entry.error) }
                    }
                    is Resource.Loading -> {
                        _state.update { it.copy(isEntnriesLoading = true) }
                    }
                }
            }
        }
    }

    private fun saveSummoner(summoner: Summoner) {
        viewModelScope.launch {
            summonersUseCases.addSummoner(summoner = summoner)
        }
    }
}