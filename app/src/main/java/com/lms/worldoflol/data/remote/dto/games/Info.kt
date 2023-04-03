package com.lms.worldoflol.data.remote.dto.games

import android.os.Build
import android.text.format.DateUtils
import androidx.annotation.RequiresApi
import com.lms.worldoflol.R
import com.lms.worldoflol.common.GameCondition
import com.lms.worldoflol.common.Lane
import com.lms.worldoflol.common.LaneRole
import com.lms.worldoflol.common.Queue
import com.lms.worldoflol.domain.model.local.GameDetail
import com.lms.worldoflol.domain.model.local.MatchDetail
import com.lms.worldoflol.domain.model.local.Participant
import com.lms.worldoflol.domain.model.local.TeamDetail
import com.lms.worldoflol.domain.model.remote.Match

data class Info(
    val gameCreation: Long,
    val gameDuration: Long,
    val gameEndTimestamp: Long,
    val gameId: Long,
    val gameMode: String,
    val gameName: String,
    val gameStartTimestamp: Long,
    val gameType: String,
    val gameVersion: String,
    val mapId: Int,
    val participants: List<ParticipantDto>,
    val platformId: String,
    val queueId: Int,
    val teams: List<Team>,
    val tournamentCode: String
)


fun Info.toMatch(participant: ParticipantDto, matchId: String, region: String) = Match(
    gameCondition = getGameCondition(participant),
    gameConditionGradient = getGameConditionGradient(getGameCondition(participant)),
    gameConditionColor = getConditionColor(getGameCondition(participant)),
    gameType = getGameType(queueId),
    championImageUrl = getChampionImageUrl(participant),
    kill = participant.kills,
    death = participant.deaths,
    assist = participant.assists,
    lane = getLaneName(participant.lane, participant.role),
    laneImage = getLaneImage(participant.lane, participant.role),
    gameCreation = getGameCreation(gameCreation),
    gameDuration = getGameDuration(gameDuration.toInt()),
    matchId = matchId,
    summonerPuuid = participant.puuid,
    summonerRegion = region
)

fun Info.toMatchDetail(participant: ParticipantDto) = MatchDetail(
    gameDetail = GameDetail(
        gameCreation = getGameCreation(gameCreation),
        gameDuration = getGameDuration(gameDuration.toInt()),
        gameType = getGameType(queueId),
        championName = participant.championName,
        gameConditionGradient = getGameConditionGradient(getGameCondition(participant)),
        gameConditionColor = getConditionColor(getGameCondition(participant)),
        gameCondition = getGameCondition(participant)
    ),
    teams = getTeamDetail(participants, participant)
)


fun getTeamDetail(participants: List<ParticipantDto>, me: ParticipantDto): List<TeamDetail> {
    val teamDetails = ArrayList<TeamDetail>()
    val winMembers = ArrayList<Participant>()
    val loseMembers = ArrayList<Participant>()
    var winkills = 0
    var winDeath = 0
    var winAssist = 0
    var losekills = 0
    var loseDeath = 0
    var loseAssist = 0

    participants.take(5).forEach {
        winkills += it.kills
        winDeath += it.deaths
        winAssist += it.assists
        winMembers.add(it.toParticipant(it, me))
    }
    participants.takeLast(5).forEach {
        losekills += it.kills
        loseDeath += it.deaths
        loseAssist += it.assists
        loseMembers.add(it.toParticipant(it, me))
    }

    val ifIWin = winMembers.any { it.puuid == me.puuid }

    teamDetails.add(
        TeamDetail(
            name = if (me.win) "BLUE Team" else "RED Team",
            stats = "($winkills / $winDeath / $winAssist)",
            gameCondition = if (me.win) GameCondition.VICTORY else GameCondition.DEFEAT,
            members = if (ifIWin) winMembers else loseMembers
        )
    )
    teamDetails.add(
        TeamDetail(
            name = if (me.win) "RED Team" else "BLUE Team",
            stats = "($losekills / $loseDeath / $loseAssist)",
            gameCondition = if (me.win) GameCondition.DEFEAT else GameCondition.VICTORY,
            members = if (ifIWin) loseMembers else winMembers
        )
    )

    return teamDetails
}

fun getGameCondition(participant: ParticipantDto): String =
    if (participant.win) GameCondition.VICTORY else GameCondition.DEFEAT

fun getConditionColor(gameCondition: String): Long =
    when (gameCondition) {
        GameCondition.VICTORY -> 0xFF0ACBE6
        GameCondition.DEFEAT -> 0xFFF12242
        else -> 0xFF999487
    }

fun getChampionImageUrl(participant: ParticipantDto): String =
    "http://ddragon.leagueoflegends.com/cdn/13.3.1/img/champion/${participant.championName}.png"

fun getGameCreation(timestamp: Long): String =
    DateUtils.getRelativeTimeSpanString(timestamp).toString()

fun getGameDuration(milliseconds: Int): String {
    val minutes = milliseconds / 60
    val seconds = milliseconds % 60
    return "$minutes:$seconds"
}

fun getGameConditionGradient(gameCondition: String): List<Long> =
    when (gameCondition) {
        GameCondition.VICTORY -> listOf(
            0xFF0ACBE6,
            0xFFEEE2CC
        )

        GameCondition.DEFEAT -> listOf(
            0xFFF12242,
            0xFFEEE2CC
        )

        else -> listOf(
            0xFF999487,
            0xFFEEE2CC
        )
    }

fun getGameType(queueId: Int) =
    when (queueId) {
        400 -> Queue.NORMAL
        420 -> Queue.SOLO
        430 -> Queue.NORMAL
        440 -> Queue.FLEX
        450 -> Queue.ARAM
        else -> Queue.NORMAL
    }


fun getLaneName(lane: String, role: String) =
    when (lane) {
        Lane.TOP -> "Top"
        Lane.JUNGLE -> "Jungle"
        Lane.MID -> "Mid"
        Lane.BOTTOM -> {
            if (role == LaneRole.CARRY) "ADC"
            else "Support"
        }

        else -> "Support"
    }


fun getLaneImage(lane: String, role: String): Int =
    when (lane) {
        Lane.TOP -> R.drawable.ic_lane_gold_top
        Lane.JUNGLE -> R.drawable.ic_lane_gold_jungle
        Lane.MID -> R.drawable.ic_lane_gold_mid
        Lane.BOTTOM -> {
            if (role == LaneRole.CARRY) R.drawable.ic_lane_gold_adc
            else R.drawable.ic_lane_gold_support
        }

        else -> R.drawable.ic_lane_gold_support
    }





