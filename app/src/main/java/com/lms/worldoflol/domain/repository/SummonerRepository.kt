package com.lms.worldoflol.domain.repository

import com.lms.worldoflol.data.remote.dto.EntryDto
import com.lms.worldoflol.data.remote.dto.SummonerDto


interface SummonerRepository {

    suspend fun getSummoner(url: String): SummonerDto

    suspend fun getEntries(url: String): List<EntryDto>

}