package com.lms.worldoflol.data.remote.dto

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName
import com.lms.worldoflol.domain.model.remote.Champion
import com.lms.worldoflol.ui.screens.main.champions.components.Difficulty

const val BASE_LOADING_URL = "http://ddragon.leagueoflegends.com/cdn/img/champion/loading/"

data class ChampionsDto(
    @SerializedName("data")
    val `data`: Map<String, ChampionDto>,
)

data class ChampionDto(
    @SerializedName("id")
    val id: String,
    @Embedded
    @SerializedName("name")
    val name: String,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("info")
    val info: Info,
)

data class Info(
    @SerializedName("difficulty")
    val difficulty: Int
)

fun ChampionDto.toChampion() = Champion(
    id = id,
    name = name,
    tags = tags,
    image = getChampionImageUrl(id),
    difficulty = getDifficulty(info.difficulty)
)

fun getChampionImageUrl(id: String) = "$BASE_LOADING_URL${id}_0.jpg"

fun getDifficulty(difficulty: Int) =
    when(difficulty){
        0 -> { Difficulty.Easy.title }
        1 -> { Difficulty.Easy.title }
        2 -> { Difficulty.Easy.title }
        3 -> { Difficulty.Easy.title }
        4 -> { Difficulty.Medium.title }
        5 -> { Difficulty.Medium.title }
        6 -> { Difficulty.Medium.title}
        7 -> { Difficulty.Medium.title}
        8 -> { Difficulty.Hard.title }
        9 -> { Difficulty.Hard.title}
        10 -> { Difficulty.Hard.title}
        else -> { Difficulty.Easy.title }
    }





