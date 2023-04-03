package com.lms.worldoflol.domain.repository

import com.lms.worldoflol.domain.model.local.SummonerEntity
import com.lms.worldoflol.domain.model.remote.Summoner
import kotlinx.coroutines.flow.Flow

interface SummonerEntityRepository {

    fun getSummoners(): Flow<List<SummonerEntity>>

    suspend fun insertSummoner(summoner: Summoner)

    suspend fun deleteSummoner(id: String)

}