package com.lms.worldoflol.domain.use_case.get_matches

import android.os.Build
import androidx.annotation.RequiresApi
import com.lms.worldoflol.common.API_KEY
import com.lms.worldoflol.common.BaseUrl
import com.lms.worldoflol.common.ErrorType
import com.lms.worldoflol.common.Region
import com.lms.worldoflol.common.RegionRouting
import com.lms.worldoflol.common.Resource
import com.lms.worldoflol.data.remote.dto.games.Info
import com.lms.worldoflol.data.remote.dto.games.toMatch
import com.lms.worldoflol.data.remote.dto.games.toMatchDetail
import com.lms.worldoflol.domain.model.local.MatchDetail
import com.lms.worldoflol.domain.model.remote.Match
import com.lms.worldoflol.domain.repository.MatchesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMatchesUseCase @Inject constructor(
    private val repository: MatchesRepository
) {
    operator fun invoke(
        region: String,
        puuid: String,
        count: Int = 10
    ): Flow<Resource<List<Match>>> = flow {
        try {
            emit(Resource.Loading())
            val matchIdsUrl = getMatchIdsUrl(region, puuid, count)
            val matchIds = repository.getMatchIds(matchIdsUrl)
            val matches = ArrayList<Match>()

            matchIds.forEach { matchId ->
                val matchUrl = getMatchUrl(region, matchId)
                val matchDto = repository.getMatch(matchUrl)
                val participant = getParticipant(matchDto.info, puuid)!!
                val match = matchDto.info.toMatch(participant, matchId, region)
                matches.add(match)
            }
            emit(Resource.Success(matches.toList()))
        } catch (e: IOException) {
            emit(Resource.Error(ErrorType.NoInternetConnection))
        } catch (e: HttpException) {
            when (e.code()) {
                404 -> emit(Resource.Error(ErrorType.NotFound))
                else -> emit(Resource.Error(ErrorType.HTTP))
            }
        }
    }

    operator fun invoke(
        region: String,
        matchId: String,
        puuid: String
    ): Flow<Resource<MatchDetail>> = flow {
        try {
            emit(Resource.Loading())
            val matchUrl = getMatchUrl(region, matchId)
            val matchDto = repository.getMatch(matchUrl)
            val participant = getParticipant(matchDto.info, puuid)!!
            val matchDetail = matchDto.info.toMatchDetail(participant)
            emit(Resource.Success(matchDetail))
        } catch (e: IOException) {
            emit(Resource.Error(ErrorType.NoInternetConnection))
        } catch (e: HttpException) {
            when (e.code()) {
                404 -> emit(Resource.Error(ErrorType.NotFound))
                else -> emit(Resource.Error(ErrorType.HTTP))
            }
        }
    }

    operator fun invoke(
        region: String,
        puuid: String,
        count: Int = 20,
        start: Int = 0
    ): Flow<Resource<List<Match>>> = flow {
        try {
            emit(Resource.Loading())
            val matchIdsUrl = getMatchIdsUrl(region, puuid, count, start)
            val matchIds = repository.getMatchIds(matchIdsUrl)
            val matches = ArrayList<Match>()

            matchIds.forEach { matchId ->
                val matchUrl = getMatchUrl(region, matchId)
                val matchDto = repository.getMatch(matchUrl)
                val participant = getParticipant(matchDto.info, puuid)!!
                val match = matchDto.info.toMatch(participant, matchId, region)
                matches.add(match)
            }
            emit(Resource.Success(matches.toList()))
        } catch (e: IOException) {
            emit(Resource.Error(ErrorType.NoInternetConnection))
        } catch (e: HttpException) {
            when (e.code()) {
                404 -> emit(Resource.Error(ErrorType.NotFound))
                else -> emit(Resource.Error(ErrorType.HTTP))
            }
        }
    }
}


fun getParticipant(info: Info, puuid: String) =
    info.participants.find { it.puuid == puuid }

fun getMatchIdsUrl(region: String, puuid: String, count: Int, start: Int = 0) =
    "https://${regionRouting(region)}.api.riotgames.com/lol/match/v5/matches/by-puuid/$puuid/ids?start=$start&count=$count&api_key=$API_KEY"

fun getMatchUrl(region: String, matchId: String) =
    "https://${regionRouting(region)}.api.riotgames.com/lol/match/v5/matches/$matchId?api_key=$API_KEY"

fun regionRouting(region: String) =
    when (region) {
        Region.EUNE -> RegionRouting.EUROPE
        Region.EUW -> RegionRouting.EUROPE
        Region.RUSSIA -> RegionRouting.EUROPE
        Region.TURKEY -> RegionRouting.EUROPE
        Region.BRAZIL -> RegionRouting.AMERICAS
        Region.LAN -> RegionRouting.AMERICAS
        Region.LAS -> RegionRouting.AMERICAS
        Region.NORTH_AMERICA -> RegionRouting.AMERICAS
        Region.KOREA -> RegionRouting.ASIA
        Region.JAPAN -> RegionRouting.ASIA
        Region.OCEANIA -> RegionRouting.SEA
        else -> BaseUrl.EUNE.url
    }




