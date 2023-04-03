package com.lms.worldoflol.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lms.worldoflol.data.local.dao.ChampionDetailTypeConverter
import com.lms.worldoflol.data.local.dao.ChampionDetailsDao
import com.lms.worldoflol.data.local.dao.ChampionsDao
import com.lms.worldoflol.data.local.dao.ChampionsTypeConverter
import com.lms.worldoflol.data.remote.dto.ChampionDetailDto
import com.lms.worldoflol.domain.model.remote.Champion

@Database(
    entities = [Champion::class, ChampionDetailDto::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(ChampionsTypeConverter::class, ChampionDetailTypeConverter::class)
abstract class ChampionsDatabase : RoomDatabase() {
    abstract val championsDao: ChampionsDao
    abstract val championDetailsDao: ChampionDetailsDao

    companion object {
        const val CHAMPIONS = "champions_db"
    }
}