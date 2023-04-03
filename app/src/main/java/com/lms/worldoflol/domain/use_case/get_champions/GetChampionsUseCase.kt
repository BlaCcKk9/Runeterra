package com.lms.worldoflol.domain.use_case.get_champions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import coil.request.ImageRequest
import coil.size.Size
import com.lms.worldoflol.common.ErrorType
import com.lms.worldoflol.common.Resource
import com.lms.worldoflol.data.remote.dto.toChampion
import com.lms.worldoflol.domain.model.remote.Champion
import com.lms.worldoflol.domain.repository.ChampionsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetChampionsUseCase @Inject constructor(
    private val repository: ChampionsRepository
) {
    operator fun invoke(): Flow<Resource<List<Champion>>> = flow {
        try {
            val championsDao = repository.getChampions()
            if (championsDao.isEmpty()) {
                emit(Resource.Loading())
                val champions = repository.getAllChampions().data.map { it.value.toChampion() }
                repository.insertChampions(champions)
                emit(Resource.Success(champions))
            } else {
                emit(Resource.Success(championsDao))
            }
        } catch (e: IOException) {
            emit(Resource.Error(ErrorType.NoInternetConnection))
        }
    }
}