package com.flagos.domain.repository

import com.flagos.domain.model.News
import com.flagos.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNewsByDate(): Flow<Resource<List<News>>>

    suspend fun markItemAsRemoved(id: String)
}