package com.lms.worldoflol.domain.model.local

import com.lms.worldoflol.data.remote.dto.games.Perks

data class Participant(
    val cs: String,
    val assists: Int,
    val championImage: String,
    val champLevel: Int,
    val deaths: Int,
    val items: List<String>,
    val kills: Int,
    val laneImage: Int,
    val runes: List<String>,
    val puuid: String,
    val summonerSpells: List<String>,
    val summonerName: String,
    val conditionColor: Long,
    val isMe: Boolean
)


