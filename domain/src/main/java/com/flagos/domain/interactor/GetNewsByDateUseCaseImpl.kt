package com.flagos.domain.interactor

import com.flagos.domain.model.News
import com.flagos.domain.repository.NewsRepository
import com.flagos.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNewsByDateUseCaseImpl @Inject constructor(
    private val newsRepository: NewsRepository,
): GetNewsByDateUseCase {

    override suspend fun invoke(): Flow<Resource<List<News>>> = flow {
        emitAll(newsRepository.getNewsByDate())
    }
}