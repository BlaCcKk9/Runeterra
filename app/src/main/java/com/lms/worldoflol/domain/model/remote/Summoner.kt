package com.lms.worldoflol.domain.model.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Summoner(
    val id: String,
    val puuid: String,
    val region: String,
    val name: String,
    val profileIconId: Int,
    val summonerLevel: Int
)
