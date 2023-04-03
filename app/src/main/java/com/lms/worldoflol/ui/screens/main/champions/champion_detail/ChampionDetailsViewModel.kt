package com.lms.worldoflol.ui.screens.main.champions.champion_detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lms.worldoflol.common.Resource
import com.lms.worldoflol.domain.use_case.get_champion_details.GetChampionDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChampionDetailsViewModel @Inject constructor(
    private val getChampionDetailsUseCase: GetChampionDetailsUseCase
): ViewModel() {

    private val _state = MutableStateFlow(ChampionDetailsState())
    val state: StateFlow<ChampionDetailsState>
        get() = _state.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            ChampionDetailsState(isLoading = true)
        )

    fun getChampionDetails(championName: String){
        viewModelScope.launch {
            getChampionDetailsUseCase(championName).collect{ response ->
                when(response){
                    is Resource.Success -> {
                        response.data?.let { championDetail ->
                            _state.update { it.copy(championDetail = championDetail, isLoading = false) }
                        }
                    }
                    is Resource.Error -> {
                       _state.update { it.copy(error = it.error) }
                    }
                    is Resource.Loading -> {
                        _state.update { it.copy(isLoading = true) }
                    }
                }
            }
        }
    }
}