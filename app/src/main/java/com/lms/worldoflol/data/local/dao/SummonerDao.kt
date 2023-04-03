package com.lms.worldoflol.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lms.worldoflol.domain.model.local.SummonerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SummonerDao {
    @Query("SELECT * FROM summonerentity")
    fun getSummoners(): Flow<List<SummonerEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSummoner(summoner: SummonerEntity)

    @Query("DELETE FROM summonerentity WHERE id = :id")
    suspend fun deleteSummoner(id: String)

}