package com.lms.worldoflol.domain.repository

import com.lms.worldoflol.data.remote.dto.ChampionDetailDto

interface ChampionDetailsRepository {

    suspend fun getChampionDetails(fromLocal: Boolean = false, name: String): ChampionDetailDto?

    suspend fun insertChampionDetails(championDetail: ChampionDetailDto)

}