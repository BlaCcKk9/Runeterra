package com.lms.worldoflol.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lms.worldoflol.domain.model.remote.Champion

@Dao
interface ChampionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChampionsList(championsList: List<Champion>)

    @Query("SELECT * FROM Champion ")
    suspend fun getChampionsList(): List<Champion>
}