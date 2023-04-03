package com.lms.worldoflol.domain.model.local

import androidx.compose.ui.graphics.Color
import com.lms.worldoflol.common.SummonerSpell
import com.lms.worldoflol.data.remote.dto.games.ParticipantDto

data class MatchDetail(
    val gameDetail: GameDetail,
    val teams: List<TeamDetail>
)

data class GameDetail(
    val gameCreation: String,
    val gameDuration: String,
    val gameType: String,
    val gameConditionGradient: List<Long>,
    val gameConditionColor: Long,
    val gameCondition: String,
    val championName: String,
)

data class TeamDetail(
    val name: String,
    val stats: String,
    val gameCondition: String,
    val members: List<Participant>
)



