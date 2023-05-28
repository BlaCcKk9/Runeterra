package com.lms.worldoflol.domain.use_case.get_entries

import android.util.Log
import com.lms.worldoflol.common.ErrorType
import com.lms.worldoflol.common.QueueType
import com.lms.worldoflol.common.Resource
import com.lms.worldoflol.data.remote.dto.EntryDto
import com.lms.worldoflol.data.remote.dto.toEntry
import com.lms.worldoflol.domain.model.remote.Entry
import com.lms.worldoflol.domain.repository.SummonerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.annotation.Nullable
import javax.inject.Inject

class GetEntriesUseCase @Inject constructor(
    private val repository: SummonerRepository
) {
    // we have only 2 item
    operator fun invoke(url: String): Flow<Resource<List<Entry?>>> = flow {
        try {
            emit(Resource.Loading())
            val entries = repository.getEntries(url).toEntries()
            emit(Resource.Success(entries))
        } catch (e: HttpException) {
            emit(Resource.Error(ErrorType.HTTP))
        } catch (e: IOException) {
            emit(Resource.Error(ErrorType.NoInternetConnection))
        }
    }
}

fun List<EntryDto>.toEntries(): List<Entry?> =
    listOf(
        getEntryByQueueType(QueueType.SOLO),
        getEntryByQueueType(QueueType.FLEX)
    )


fun List<EntryDto>.getEntryByQueueType(type: String) =
    dropWhile { it.queueType != type }
        .ifEmpty { null }
        ?.single()
        ?.toEntry()


