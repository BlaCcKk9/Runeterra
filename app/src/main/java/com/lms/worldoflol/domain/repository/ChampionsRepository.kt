package com.lms.worldoflol.domain.repository

import com.lms.worldoflol.data.local.dao.ChampionsDao
import com.lms.worldoflol.data.remote.dto.ChampionsDto
import com.lms.worldoflol.domain.model.remote.Champion

interface ChampionsRepository {

    suspend fun getAllChampions(): ChampionsDto

    suspend fun getChampions(): List<Champion>

    suspend fun insertChampions(champions: List<Champion>)

    suspend fun getChampionDetail()

}