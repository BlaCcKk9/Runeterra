package com.lms.worldoflol.domain.repository

import com.lms.worldoflol.data.remote.dto.games.MatchDto
import com.lms.worldoflol.data.remote.dto.games.MatchesIdsDto

interface MatchesRepository {

    suspend fun getMatchIds(url: String): MatchesIdsDto

    suspend fun getMatch(url: String): MatchDto
}