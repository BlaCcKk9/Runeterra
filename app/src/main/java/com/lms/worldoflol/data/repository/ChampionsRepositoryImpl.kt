package com.lms.worldoflol.data.repository

import com.lms.worldoflol.data.local.dao.ChampionsDao
import com.lms.worldoflol.data.remote.WorlOfLolApi
import com.lms.worldoflol.data.remote.dto.ChampionsDto
import com.lms.worldoflol.domain.model.remote.Champion
import com.lms.worldoflol.domain.repository.ChampionsRepository
import javax.inject.Inject

class ChampionsRepositoryImpl @Inject constructor(
    private val api: WorlOfLolApi,
    private val dao: ChampionsDao,
): ChampionsRepository {

    override suspend fun getAllChampions(): ChampionsDto {
        return api.getAllChampions()
    }

    override suspend fun getChampions(): List<Champion> {
        return dao.getChampionsList()
    }

    override suspend fun insertChampions(champions: List<Champion>) {
        dao.insertChampionsList(champions)
    }


    override suspend fun getChampionDetail() {}

}