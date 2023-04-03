package com.lms.worldoflol.domain.model.remote

data class ChampionDetail(
    val championId: String,
    val championName: String,
    val championTitle: String,
    val championImage: String,
    val lore: String,
    val blurb: String,
    val skins: List<Skin>,
    val role: String,
    val roleImage: Int,
    val difficulty: String,
    val difficultyImage: Int,
    val spells: List<Spell>,
)

data class Skin(
    val id: String,
    val name: String,
    val imageUrl: String
)

data class Spell(
    val name: String,
    val title: String,
    val imageUrl: String,
    val description: String,
    var isSelected: Boolean = false
)


