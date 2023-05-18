package com.lms.worldoflol.data.repository

import com.lms.worldoflol.data.local.database.SummonerDatabase
import com.lms.worldoflol.data.remote.WorlOfLolApi
import com.lms.worldoflol.data.remote.dto.EntryDto
import com.lms.worldoflol.data.remote.dto.SummonerDto
import com.lms.worldoflol.domain.repository.SummonerRepository
import javax.inject.Inject

class SummonerRepositoryImpl @Inject constructor(
    private val api: WorlOfLolApi
) : SummonerRepository {
    override suspend fun getSummoner(url: String): SummonerDto {
        return api.getSummoner(url)
    }

    override suspend fun getEntries(url: String): List<EntryDto> {
        return api.getEntries(url)
    }
}