package com.flagos.domain.interactor

import android.util.Log
import com.flagos.domain.repository.NewsRepository
import javax.inject.Inject

class GetMarkItemAsRemovedUseCaseImpl@Inject constructor(
    private val newsRepository: NewsRepository,
): GetMarkItemAsRemovedUseCase {

    override suspend fun invoke(id: String) {
        Log.d("Bambino", "invoke use case: $id")
        newsRepository.markItemAsRemoved(id)
    }
}