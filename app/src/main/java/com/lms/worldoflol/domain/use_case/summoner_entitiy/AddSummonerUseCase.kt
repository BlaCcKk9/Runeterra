package com.lms.worldoflol.domain.use_case.summoner_entitiy

import com.lms.worldoflol.domain.model.local.SummonerEntity
import com.lms.worldoflol.domain.model.remote.Summoner
import com.lms.worldoflol.domain.repository.SummonerEntityRepository

class AddSummonerUseCase(
    private val repository: SummonerEntityRepository
) {
    suspend operator fun invoke(summoner: Summoner) {
        repository.insertSummoner(summoner)
    }
}