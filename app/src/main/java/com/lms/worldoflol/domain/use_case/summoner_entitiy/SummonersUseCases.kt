package com.lms.worldoflol.domain.use_case.summoner_entitiy

data class SummonersUseCases(
    val getSummoners: GetSummonersUseCase,
    val deleteSummoner: DeleteSummonerUseCase,
    val addSummoner: AddSummonerUseCase
)
