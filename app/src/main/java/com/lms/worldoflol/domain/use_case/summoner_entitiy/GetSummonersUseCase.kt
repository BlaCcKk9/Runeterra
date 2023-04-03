package com.lms.worldoflol.domain.use_case.summoner_entitiy

import android.util.Log
import com.lms.worldoflol.domain.model.local.SummonerEntity
import com.lms.worldoflol.domain.model.remote.Summoner
import com.lms.worldoflol.domain.repository.SummonerEntityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach

class GetSummonersUseCase(
    private val repository: SummonerEntityRepository
) {
    operator fun invoke(): Flow<List<Summoner>> = flow {
        val summoners = arrayListOf<Summoner>()

        repository.getSummoners().collect {
            it.forEach { entity ->
                summoners.add(
                    Summoner(
                        id = entity.id,
                        name = entity.name,
                        profileIconId = entity.profileIconId,
                        summonerLevel = entity.summonerLevel.toInt(),
                        region = entity.region,
                        puuid = entity.puuid
                    )
                )
            }
            emit(summoners)
        }
    }

}