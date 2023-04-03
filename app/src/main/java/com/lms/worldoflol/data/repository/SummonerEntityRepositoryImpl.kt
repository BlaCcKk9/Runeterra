package com.lms.worldoflol.data.repository

import android.util.Log
import com.lms.worldoflol.data.local.dao.SummonerDao
import com.lms.worldoflol.domain.model.local.SummonerEntity
import com.lms.worldoflol.domain.model.remote.Summoner
import com.lms.worldoflol.domain.repository.SummonerEntityRepository
import kotlinx.coroutines.flow.Flow

class SummonerEntityRepositoryImpl(
    private val summonerDao: SummonerDao
): SummonerEntityRepository {

    override fun getSummoners(): Flow<List<SummonerEntity>> {
        return summonerDao.getSummoners()
    }

    override suspend fun insertSummoner(summoner: Summoner) {
        val summonerEntity = SummonerEntity(
            id = summoner.id,
            summonerId = summoner.id,
            name = summoner.name,
            profileIconId = summoner.profileIconId,
            summonerLevel = summoner.summonerLevel.toString(),
            region = summoner.region,
            puuid = summoner.puuid
        )
        return summonerDao.insertSummoner(summonerEntity)
    }

    override suspend fun deleteSummoner(id: String) {
        return summonerDao.deleteSummoner(id)
    }
}