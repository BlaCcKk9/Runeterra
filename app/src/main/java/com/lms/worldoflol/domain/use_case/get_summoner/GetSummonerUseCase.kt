package com.lms.worldoflol.domain.use_case.get_summoner

import com.lms.worldoflol.common.ErrorType
import com.lms.worldoflol.common.Resource
import com.lms.worldoflol.common.getBaseUrl
import com.lms.worldoflol.common.getSummonerUrl
import com.lms.worldoflol.data.remote.dto.toSummoner
import com.lms.worldoflol.domain.model.remote.Summoner
import com.lms.worldoflol.domain.repository.SummonerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

import javax.inject.Inject

class GetSummonerUseCase @Inject constructor(
    private val repository: SummonerRepository
) {
    operator fun invoke(region: String, name: String): Flow<Resource<Summoner>> = flow {
        try {
            emit(Resource.Loading())
            val summoner = repository.getSummoner(url(region, name)).toSummoner(region)
            emit(Resource.Success(summoner))
        } catch (e: IOException) {
            emit(Resource.Error(ErrorType.NoInternetConnection))
        } catch (e: HttpException) {
            when (e.code()) {
                404 -> emit(Resource.Error(ErrorType.NotFound))
                else -> emit(Resource.Error(ErrorType.HTTP))
            }
        }
    }

    private fun url(region: String, name: String): String =
        getSummonerUrl(getBaseUrl(region), name)

}
