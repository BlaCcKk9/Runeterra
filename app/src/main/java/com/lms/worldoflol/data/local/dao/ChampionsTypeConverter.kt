package com.lms.worldoflol.data.local.dao

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lms.worldoflol.data.remote.dto.ChampionDto
import com.lms.worldoflol.data.remote.dto.ChampionsDto
import com.lms.worldoflol.data.remote.dto.Info
import com.lms.worldoflol.domain.model.remote.Champion
import com.lms.worldoflol.ui.screens.main.champions.components.Difficulty

open class ChampionsTypeConverter {

    @TypeConverter
    fun fromString(value: String): List<String> {
        val mapType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, mapType)
    }

    @TypeConverter
    fun fromList(list: List<String>):String {
        return Gson().toJson(list)
    }
}