package com.lms.worldoflol.data.repository

import com.lms.worldoflol.data.local.dao.ChampionDetailsDao
import com.lms.worldoflol.data.remote.WorlOfLolApi
import com.lms.worldoflol.data.remote.dto.ChampionDetailDto
import com.lms.worldoflol.domain.repository.ChampionDetailsRepository
import javax.inject.Inject

class ChampionDetailsRepositoryImpl @Inject constructor(
    private val api: WorlOfLolApi,
    private val dao: ChampionDetailsDao,
): ChampionDetailsRepository {
    override suspend fun getChampionDetails(fromLocal: Boolean, name: String): ChampionDetailDto? {
        return if (fromLocal) dao.getChampionInfo(name)
        else api.getChampionDetails(name).data.map { it.value }[0]
    }

    override suspend fun insertChampionDetails(championDetail: ChampionDetailDto){
        dao.insertChampionInfo(championDetail)
    }
}