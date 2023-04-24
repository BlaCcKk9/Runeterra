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
import javax.inject.Inject

class GetEntriesUseCase @Inject constructor(
    private val repository: SummonerRepository
) {
    // we have only 2 item
    operator fun invoke(url: String): Flow<Resource<List<Entry?>>> = flow {
        try {
            emit(Resource.Loading())
            val entries = entriesMapper(repository.getEntries(url))
            emit(Resource.Success(entries))
        } catch (e: HttpException) {
            emit(Resource.Error(ErrorType.HTTP))
        } catch (e: IOException) {
            emit(Resource.Error(ErrorType.NoInternetConnection))
        }
    }
}

private fun entriesMapper(
    listOfEntries: List<EntryDto>
): List<Entry?> {

    var soloEntry: Entry? = null
    var flexEntry: Entry? = null

    val entries = listOfEntries.dropWhile { entry ->
        entry.queueType != QueueType.SOLO &&
                entry.queueType != QueueType.FLEX
    }

    when (entries.size) {
        2 -> {
            soloEntry = entries[0].toEntry()
            flexEntry = entries[1].toEntry()
        }

        else -> {
            if (entries.isNotEmpty()) {
                if (entries[0].queueType == QueueType.SOLO) soloEntry = entries[0].toEntry()
                else flexEntry = entries[0].toEntry()
            }
        }
    }

    return arrayListOf(soloEntry, flexEntry)
}
