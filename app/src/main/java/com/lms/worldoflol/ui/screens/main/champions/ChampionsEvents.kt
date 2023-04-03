package com.lms.worldoflol.ui.screens.main.champions

import com.lms.worldoflol.ui.screens.main.champions.components.Difficulty

sealed class ChampionsEvents {
    class SearchChampions(var query: String): ChampionsEvents()
    class FilterChampionsRole(var role: String): ChampionsEvents()
    class FilterChampionsDifficulty(var difficulty: Difficulty): ChampionsEvents()
}