package com.lms.worldoflol.ui.screens.main.profile.match_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lms.worldoflol.common.Resource
import com.lms.worldoflol.domain.use_case.get_matches.GetMatchesUseCase
import com.lms.worldoflol.domain.use_case.get_summoner.GetSummonerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchDetailViewModel @Inject constructor(
    private val getSummonerUseCase: GetSummonerUseCase,
    private val getMatchesUseCase: GetMatchesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MatchDetailState())
    val state: StateFlow<MatchDetailState>
        get() = _state.stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            MatchDetailState(isLoading = true)
        )

    fun onEvent(event: MatchDetailEvents) {
        when (event) {
            is MatchDetailEvents.FetchMatchDetail -> {
                getMatchDetail(
                    region = event.region,
                    matchId = event.matchId,
                    puuid = event.puuid
                )
            }

            is MatchDetailEvents.GetSummoner -> {
                _state.update { it.copy(summoner = null) }
                getSummoner(event.summonerName, event.region)
            }
        }
    }

    private fun getMatchDetail(region: String, matchId: String, puuid: String) {
        viewModelScope.launch {
            getMatchesUseCase(
                region = region,
                matchId = matchId,
                puuid = puuid
            ).collect { matchDetail ->
                when (matchDetail) {
                    is Resource.Success -> {
                        _state.update { it.copy(matchDetail = matchDetail.data, isLoading = false) }
                    }

                    is Resource.Error -> {
                        _state.update { it.copy(error = matchDetail.error) }
                    }

                    is Resource.Loading -> {
                        _state.update { it.copy(isLoading = true) }
                    }
                }
            }
        }
    }

    private fun getSummoner(summonerName: String, region: String) {
        getSummonerUseCase(region, summonerName).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.update { it.copy(summoner = result.data) }
                }

                is Resource.Error -> {
                    _state.update { it.copy(summoner = null) }
                }

                is Resource.Loading -> {
                    _state.update { it.copy(summoner = null) }
                }
            }
        }.launchIn(viewModelScope)
    }
}