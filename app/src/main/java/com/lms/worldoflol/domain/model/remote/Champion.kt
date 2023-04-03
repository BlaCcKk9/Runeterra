package com.lms.worldoflol.domain.model.remote

import androidx.room.Entity

@Entity(primaryKeys = ["id"])
data class Champion(
    val id: String,
    val name: String,
    val tags: List<String>,
    val image: String,
    val difficulty: String
)


