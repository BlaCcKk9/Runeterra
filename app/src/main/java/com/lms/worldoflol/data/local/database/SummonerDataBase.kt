package com.lms.worldoflol.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lms.worldoflol.data.local.dao.SummonerDao
import com.lms.worldoflol.domain.model.local.SummonerEntity

@Database(
    entities = [SummonerEntity::class],
    version = 1,
)
abstract class SummonerDatabase : RoomDatabase() {
    abstract val summonerDao: SummonerDao

    companion object {
        const val SUMMONER = "summoner_db"
    }
}