package com.flagos.domain.di

import com.flagos.domain.interactor.GetNewsByDateUseCase
import com.flagos.domain.interactor.GetNewsByDateUseCaseImpl
import com.flagos.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object InteractionModule {

    @Provides
    fun provideGetNewsByDateUseCase(
        newsRepository: NewsRepository
    ): GetNewsByDateUseCase = GetNewsByDateUseCaseImpl(newsRepository)
}
