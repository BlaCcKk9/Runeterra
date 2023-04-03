package com.lms.worldoflol.ui.screens.main.search

import android.util.Log
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.text.toUpperCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lms.worldoflol.common.Resource
import com.lms.worldoflol.common.getBaseUrl
import com.lms.worldoflol.common.getSummonerUrl
import com.lms.worldoflol.domain.model.remote.Summoner
import com.lms.worldoflol.domain.use_case.get_summoner.GetSummonerUseCase
import com.lms.worldoflol.domain.use_case.summoner_entitiy.SummonersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSummonerUseCase: GetSummonerUseCase,
    private val summonersUseCases: SummonersUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(SearchState())
    val state: StateFlow<SearchState> = _state

    private var recentSearches = listOf<Summoner>()

    private var getRecentSearchesJob: Job? = null

    init {
        getRecentSearches()
    }

    fun onEvent(event: SearchEvents) {
        when (event) {
            is SearchEvents.DeleteSummoner -> { deleteSummoner(event.summoner) }
            is SearchEvents.DeleteAll -> { deleteAll() }
            is SearchEvents.GetRecentSearches -> { getRecentSearchBySearch(event.name) }
            is SearchEvents.GetSummoner -> {
                _state.update { it.copy(summoner = null, onSearchResult = true) }
                getSummoner(event.summonerName, event.region)
            }
        }
    }

    private fun deleteSummoner(summoner: Summoner) {
        viewModelScope.launch {
            summonersUseCases.deleteSummoner.invoke(summoner.id)
            getRecentSearches()
        }

    }

    private fun deleteAll() {
        viewModelScope.launch {
            _state.value.summoners.forEach {
                summonersUseCases.deleteSummoner.invoke(it.id)
            }
            getRecentSearches()
        }
    }

    private fun getRecentSearchBySearch(name: String){
        if (name.isNotBlank()){
            val recentSearchesByName = recentSearches.filter {
                it.name.lowercase(Locale.ROOT).startsWith(name.lowercase(Locale.ROOT))
            }
            val showTitle = recentSearchesByName.isNotEmpty()
            _state.update {
                it.copy(
                    summoner = null,
                    summoners = recentSearchesByName,
                    onSearchResult = false,
                    showTitle = showTitle
                )
            }
        } else getRecentSearches()
    }

    private fun getRecentSearches() {
        getRecentSearchesJob?.cancel()
        getRecentSearchesJob = summonersUseCases.getSummoners()
            .onEach { summoners ->
                recentSearches = summoners
                _state.value = if (summoners.isEmpty())
                    state.value.copy(summoners = summoners, onSearchResult = false, showTitle = false)
                else state.value.copy(summoners = summoners, onSearchResult = false, showTitle = true)

            }
            .launchIn(viewModelScope)
    }

    private fun getSummoner(summonerName: String, region: String) {
        getSummonerUseCase(region, summonerName).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            summoner = result.data,
                            isLoading = false,
                            error = null,
                            showTitle = true,
                            selectedRegion = region
                        )
                    }
                }

                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            error = result.error,
                            isLoading = false,
                            showTitle = false
                        )
                    }
                }

                is Resource.Loading -> {
                    _state.update { it.copy(isLoading = true) }
                }
            }
        }.launchIn(viewModelScope)
    }
}