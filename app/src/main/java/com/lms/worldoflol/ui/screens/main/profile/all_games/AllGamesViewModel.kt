package com.lms.worldoflol.ui.screens.main.profile.all_games

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lms.worldoflol.common.Resource
import com.lms.worldoflol.domain.model.remote.Match
import com.lms.worldoflol.domain.use_case.get_matches.GetMatchesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

const val PAGE_SIZE = 20

@HiltViewModel
class AllGamesViewModel @Inject constructor(
    private val getMatchesUseCase: GetMatchesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(AllGamesState())
    val state: StateFlow<AllGamesState>
        get() = _state.stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            AllGamesState(isLoading = true)
        )

    private val curPage = 0
    private val matches: ArrayList<Match> = ArrayList()

    fun onEvent(event: AllGamesEvents) {
        when (event) {
            is AllGamesEvents.GetMatches -> {
                getMatches(event.region, event.puuid)
            }
        }
    }

    private fun getMatches(
        region: String,
        puuid: String,
    ) {
        viewModelScope.launch {
            getMatchesUseCase(
                region = region,
                puuid = puuid,
                count = PAGE_SIZE,
                start = curPage * PAGE_SIZE
            ).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        response.data!!.forEach {
                            matches.add(it)
                        }
                        _state.update {
                            it.copy(
                                matches = matches,
                                isLoading = false,
                                endReached = curPage * PAGE_SIZE >= response.data.count()
                            )
                        }
                    }

                    is Resource.Error -> {
                        _state.update { it.copy(error = response.error) }
                    }

                    is Resource.Loading -> {
                        _state.update { it.copy(isLoading = true) }
                    }
                }
            }
        }
    }

}