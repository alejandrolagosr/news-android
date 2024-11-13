package com.flagos.domain.interactor

import com.flagos.domain.model.News
import com.flagos.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface GetNewsByDateUseCase {

    suspend operator fun invoke(): Flow<Resource<List<News>>>
}