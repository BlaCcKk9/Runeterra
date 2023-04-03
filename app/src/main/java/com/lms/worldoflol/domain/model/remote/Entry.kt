package com.lms.worldoflol.domain.model.remote

import androidx.compose.ui.graphics.Color

data class Entry(
    val leaguePoints: String,
    val losses: String,
    val queueType: String,
    val rank: String,
    val tier: String,
    val wins: String,
    val winRate: String,
    val tierIcon : Int,
    var tierTexColor: List<Color>
)
