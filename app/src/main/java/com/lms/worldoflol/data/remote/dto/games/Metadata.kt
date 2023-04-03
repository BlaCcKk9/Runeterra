package com.lms.worldoflol.data.remote.dto.games

data class Metadata(
    val dataVersion: String,
    val matchId: String,
    val participants: List<String>
)