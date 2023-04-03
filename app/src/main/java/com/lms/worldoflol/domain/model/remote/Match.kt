package com.lms.worldoflol.domain.model.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Match(
    val gameCreation: String,
    val gameDuration: String,
    val gameType: String,
    val gameCondition: String,
    val gameConditionGradient: List<Long>,
    val gameConditionColor: Long,
    val kill: Int,
    val death: Int,
    val assist: Int,
    val championImageUrl: String,
    val lane: String,
    val laneImage: Int,
    val matchId: String,
    val summonerPuuid: String,
    val summonerRegion: String
)
