package com.lms.worldoflol.ui.screens.main.champions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lms.worldoflol.common.Resource
import com.lms.worldoflol.domain.model.remote.Champion
import com.lms.worldoflol.domain.use_case.get_champions.GetChampionsUseCase
import com.lms.worldoflol.ui.screens.main.champions.components.Difficulty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class ChampionsViewModel @Inject constructor(
    private val getChampionsUseCase: GetChampionsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ChampionsState())
    val state: StateFlow<ChampionsState>
        get() = _state.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            ChampionsState(isLoading = true)
        )

    private var query = _state.value.championSearchQuery
    private var role = _state.value.selectedRole
    private var difficulty = _state.value.selectedDifficulty
    private val allChampions = ArrayList<Champion>()

    init {
        getChampions()
    }

    fun onEvent(event: ChampionsEvents) {
        when (event) {
            is ChampionsEvents.SearchChampions -> {
                _state.value.championSearchQuery = event.query
                this.query = event.query
                filterChampions()
            }
            is ChampionsEvents.FilterChampionsRole -> {
                _state.value.selectedRole = event.role
                this.role = event.role
                filterChampions()
            }
            is ChampionsEvents.FilterChampionsDifficulty -> {
                _state.value.selectedDifficulty = event.difficulty.title
                this.difficulty = event.difficulty.title
                filterChampions()
            }
        }
    }

    private fun getChampions() {
        viewModelScope.launch {
            getChampionsUseCase().collect { champions ->
                when (champions) {
                    is Resource.Success -> {
                        allChampions.clear()
                        allChampions.addAll(champions.data as ArrayList)
                        _state.update { it.copy(champions = champions.data, isLoading = false) }
                    }

                    is Resource.Error -> {
                        _state.update { it.copy(error = champions.error) }
                    }

                    is Resource.Loading -> {
                        _state.update { it.copy(isLoading = true) }
                    }
                }
            }
        }
    }

    private fun filterChampions() {
        val isRoleEmpty =  role.isEmpty()
        val isDifficultyEmpty = difficulty.isEmpty()

        val filteredChampions = allChampions
            .filter { it.name.lowercase(Locale.ROOT).startsWith(query.lowercase(Locale.ROOT)) }
            .filter { if (isRoleEmpty) true else it.tags.contains(role) }
            .filter { if (isDifficultyEmpty) true else it.difficulty == difficulty }

        _state.update {
            it.copy(
                isLoading = false,
                champions = filteredChampions
            )
        }
    }
}
