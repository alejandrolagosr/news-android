package com.flagos.data.repository

import com.flagos.data.database.model.AppDatabase
import com.flagos.data.mapper.toDomain
import com.flagos.data.mapper.toEntity
import com.flagos.data.networking.NewsApi
import com.flagos.domain.model.News
import com.flagos.domain.repository.NewsRepository
import com.flagos.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val database: AppDatabase
) : NewsRepository {

    private val newsDao = database.newsDao()

    override suspend fun getNewsByDate(): Flow<Resource<List<News>>> {
        return flow {
            emit(Resource.loading())
            try {
                val response = newsApi.getNewsByDate()
                if (response.isSuccessful) {
                    response.body()?.hits
                        ?.filter { it.url.isNullOrEmpty().not() }
                        ?.map { it.toDomain() }
                        ?.also { news ->
                            // Save to Room database
                            newsDao.clearNews()
                            newsDao.insertNews(news.map { it.toEntity() })
                            emit(Resource.success(news))
                        }
                } else {
                    // Load data from the database on error
                    val cachedNews = newsDao.getNews().first().map { it.toDomain() }
                    emit(Resource.success(cachedNews))
                }
            } catch (throwable: Throwable) {
                // Fall back to the database on exception
                val cachedNews = newsDao.getNews().first().map { it.toDomain() }
                emit(Resource.error(throwable.message.orEmpty(), cachedNews))
            }
        }
    }
}