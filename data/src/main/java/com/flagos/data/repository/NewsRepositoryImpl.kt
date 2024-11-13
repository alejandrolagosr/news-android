package com.flagos.data.repository

import com.flagos.data.mapper.toDomain
import com.flagos.data.networking.NewsApi
import com.flagos.domain.model.News
import com.flagos.domain.repository.NewsRepository
import com.flagos.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : NewsRepository {

    override suspend fun getNewsByDate(): Flow<Resource<List<News>>> {
        return flow {
            emit(Resource.loading())
            try {
                val response = newsApi.getNewsByDate()
                if (response.isSuccessful) {
                    response.body()?.hits
                        ?.filter { it.url.isNullOrEmpty().not() }
                        ?.map { it.toDomain() }
                        ?.let {
                            emit(Resource.success(it))
                        }
                } else {
                    emit(Resource.error("An error occurred"))
                }
            } catch (throwable: Throwable) {
                emit(Resource.error(throwable.message.orEmpty()))
            }
        }
    }
}