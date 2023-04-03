package com.lms.worldoflol.data.remote

import com.lms.worldoflol.data.remote.dto.ChampionInfoDto
import com.lms.worldoflol.data.remote.dto.ChampionsDto
import com.lms.worldoflol.data.remote.dto.EntryDto
import com.lms.worldoflol.data.remote.dto.SummonerDto
import com.lms.worldoflol.data.remote.dto.games.MatchDto
import com.lms.worldoflol.data.remote.dto.games.MatchesIdsDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface WorlOfLolApi {
    @GET
    suspend fun getSummoner(@Url url: String) : SummonerDto

    @GET
    suspend fun getEntries(@Url url: String): List<EntryDto>

    @GET
    suspend fun getMatchesIds(@Url url: String): MatchesIdsDto

    @GET
    suspend fun getMatch(@Url url: String): MatchDto

    @GET("http://ddragon.leagueoflegends.com/cdn/12.23.1/data/en_US/champion.json")
    suspend fun getAllChampions(): ChampionsDto

    @GET("http://ddragon.leagueoflegends.com/cdn/12.23.1/data/en_US/champion/{name}.json")
    suspend fun getChampionDetails(@Path("name") name: String): ChampionInfoDto
}