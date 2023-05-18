package com.lms.worldoflol.domain.model.remote

import com.lms.worldoflol.domain.model.local.SummonerEntity
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

fun Summoner.toEntity() =
    SummonerEntity(
        id = this.id,
        summonerId = this.id,
        name = this.name,
        profileIconId = this.profileIconId,
        summonerLevel = this.summonerLevel.toString(),
        region = this.region,
        puuid = this.puuid
    )