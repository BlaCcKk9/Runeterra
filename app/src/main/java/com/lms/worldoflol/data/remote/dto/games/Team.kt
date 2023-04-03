package com.lms.worldoflol.data.remote.dto.games

data class Team(
    val bans: List<Ban>,
    val objectives: Objectives,
    val teamId: Int,
    val win: Boolean
)