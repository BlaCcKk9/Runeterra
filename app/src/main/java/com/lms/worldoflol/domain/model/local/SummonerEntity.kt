package com.lms.worldoflol.domain.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SummonerEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String = "",
    val summonerId: String,
    val name: String,
    val profileIconId: Int,
    val summonerLevel: String,
    val region: String,
    val puuid: String
)
