package com.lms.worldoflol.domain.use_case.summoner_entitiy

import com.lms.worldoflol.domain.model.local.SummonerEntity
import com.lms.worldoflol.domain.repository.SummonerEntityRepository
import kotlinx.coroutines.flow.Flow


class DeleteSummonerUseCase(
    private val repository: SummonerEntityRepository
) {
    suspend operator fun invoke(id: String) {
        repository.deleteSummoner(id)
    }
}