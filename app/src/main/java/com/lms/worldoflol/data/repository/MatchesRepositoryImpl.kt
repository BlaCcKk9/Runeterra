package com.lms.worldoflol.data.repository

import com.lms.worldoflol.data.remote.WorlOfLolApi
import com.lms.worldoflol.data.remote.dto.games.MatchDto
import com.lms.worldoflol.data.remote.dto.games.MatchesIdsDto
import com.lms.worldoflol.domain.repository.MatchesRepository
import javax.inject.Inject

class MatchesRepositoryImpl @Inject constructor(
    val api: WorlOfLolApi,
) : MatchesRepository {
    override suspend fun getMatchIds(url: String): MatchesIdsDto = api.getMatchesIds(url)

    override suspend fun getMatch(url: String): MatchDto = api.getMatch(url)
}