package com.lms.worldoflol.data.remote.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.lms.worldoflol.domain.model.remote.ChampionDetail


data class ChampionInfoDto(
    @SerializedName("data")
    val `data`: Map<String, ChampionDetailDto>,
    @SerializedName("format")
    val format: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("version")
    val version: String
)

@Entity
data class ChampionDetailDto(
    @SerializedName("blurb")
    val blurb: String,
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    @SerializedName("info")
    val info: Info,
    @SerializedName("lore")
    val lore: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("passive")
    val passive: PassiveDto,
    @SerializedName("skins")
    val skins: List<SkinDto>,
    @SerializedName("spells")
    val spells: List<SpellDto>,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("title")
    val title: String
) {
    data class SkinDto(
        @SerializedName("chromas")
        val chromas: Boolean,
        @SerializedName("id")
        val id: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("num")
        val num: Int
    )

    data class SpellDto(
        @SerializedName("description")
        val description: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("image")
        val image: ImageDto,
        @SerializedName("name")
        val name: String,
    )

    data class PassiveDto(
        @SerializedName("description")
        val description: String,
        @SerializedName("image")
        val image: ImageDto,
        @SerializedName("name")
        val name: String
    )
}


data class ImageDto(
    @SerializedName("full")
    val full: String,
)


fun spellImage(image: ImageDto) =
    "https://ddragon.leagueoflegends.com/cdn/10.16.1/img/spell/${image.full}"


