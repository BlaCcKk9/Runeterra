package com.lms.worldoflol.data.remote.dto

import com.lms.worldoflol.domain.model.remote.Summoner

data class SummonerDto(
    val accountId: String,
    val id: String,
    val name: String,
    val profileIconId: Int,
    val puuid: String,
    val revisionDate: Long,
    val summonerLevel: Int
)

fun SummonerDto.toSummoner(region: String) = Summoner(
    id = id,
    puuid = puuid,
    region = region,
    name = name,
    profileIconId = profileIconId,
    summonerLevel = summonerLevel
)