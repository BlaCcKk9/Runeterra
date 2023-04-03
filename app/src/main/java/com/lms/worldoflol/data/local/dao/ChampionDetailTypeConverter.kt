package com.lms.worldoflol.data.local.dao

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lms.worldoflol.data.remote.dto.ChampionDetailDto
import com.lms.worldoflol.data.remote.dto.Info

class ChampionDetailTypeConverter {
    @TypeConverter
    fun fromPassiveString(value: String): ChampionDetailDto.PassiveDto {
        val mapType = object : TypeToken<ChampionDetailDto.PassiveDto>() {}.type
        return Gson().fromJson(value, mapType)
    }

    @TypeConverter
    fun fromImage(passive: ChampionDetailDto.PassiveDto):String {
        return Gson().toJson(passive)
    }

    @TypeConverter
    fun fromSkinListString(value: String): List<ChampionDetailDto.SkinDto> {
        val mapType = object : TypeToken<List<ChampionDetailDto.SkinDto>>() {}.type
        return Gson().fromJson(value, mapType)
    }

    @TypeConverter
    fun fromSkinList(skin: List<ChampionDetailDto.SkinDto>):String {
        return Gson().toJson(skin)
    }

    @TypeConverter
    fun fromSpellListString(value: String): List<ChampionDetailDto.SpellDto> {
        val mapType = object : TypeToken<List<ChampionDetailDto.SpellDto>>() {}.type
        return Gson().fromJson(value, mapType)
    }

    @TypeConverter
    fun fromSpellList(spell: List<ChampionDetailDto.SpellDto>):String {
        return Gson().toJson(spell)
    }

    @TypeConverter
    fun fromInfoString(value: String): Info{
        val mapType = object : TypeToken<Info>() {}.type
        return Gson().fromJson(value, mapType)
    }

    @TypeConverter
    fun fromInfo(info: Info): String{
        return  Gson().toJson(info)
    }
}