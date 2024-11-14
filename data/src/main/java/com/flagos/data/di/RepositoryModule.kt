package com.flagos.data.di

import com.flagos.data.database.model.AppDatabase
import com.flagos.data.networking.NewsApi
import com.flagos.data.repository.NewsRepositoryImpl
import com.flagos.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(newsApi: NewsApi, database: AppDatabase): NewsRepository =
        NewsRepositoryImpl(newsApi, database)
}