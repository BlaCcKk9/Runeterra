package com.lms.worldoflol.data.remote.dto.games

data class Style(
    val description: String,
    val selections: List<Selection>,
    val style: Int
)