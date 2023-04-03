package com.lms.worldoflol.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lms.worldoflol.data.remote.dto.ChampionDetailDto
import com.lms.worldoflol.data.remote.dto.ChampionInfoDto

@Dao
interface ChampionDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChampionInfo(championDetail: ChampionDetailDto)

    @Query("SELECT * FROM ChampionDetailDto WHERE name = :name_")
    suspend fun getChampionInfo(name_: String): ChampionDetailDto?
}