package com.lms.worldoflol.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lms.worldoflol.common.*
import com.lms.worldoflol.domain.model.remote.Summoner
import com.lms.worldoflol.domain.use_case.get_summoner.GetSummonerUseCase
import com.lms.worldoflol.ui.screens.auth.LoginEvent.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getSummonerUseCase: GetSummonerUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState>
        get() = _state.stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            LoginState()
        )

    fun onEvent(event: LoginEvent) {

        when (event) {
            is OnLoginClick -> {
                getSummoner(event.region, event.summonerName, event.onSuccess)
            }
            is OnRefresh -> {
                _state.update { LoginState() }
            }
            is OnSelectRegionClick -> {
                _state.update { it.copy(isSelectRegionClicked = true) }
            }
            is OnRegionClick -> {
                _state.update {
                    it.copy(
                        selectedRegionName = event.region,
                        isSelectRegionClicked = false
                    )
                }
            }
        }
    }

    private fun getSummoner(
        region: String,
        summonerName: String,
        onSuccess: (summoner: Summoner) -> Unit
    ) {
        fetchSummoner(summonerName, region, onSuccess)
    }

    private fun fetchSummoner(
        summonerName: String,
        region: String,
        onSuccess: (summoner: Summoner) -> Unit
    ) {
        getSummonerUseCase(region, summonerName).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    result.data?.apply {
                        onSuccess(this)
                    }
                }
                is Resource.Error -> {
                    _state.update { it.copy(error = result.error, isLoading = false) }
                }
                is Resource.Loading -> {
                    _state.update { it.copy(isLoading = true) }
                }
            }
        }.launchIn(viewModelScope)
    }
}