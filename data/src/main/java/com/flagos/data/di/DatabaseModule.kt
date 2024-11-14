package com.flagos.data.di

import android.content.Context
import androidx.room.Room
import com.flagos.data.database.NewsDao
import com.flagos.data.database.RemovedItemsDao
import com.flagos.data.database.model.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "news_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideNewsDao(database: AppDatabase): NewsDao {
        return database.newsDao()
    }

    @Provides
    fun provideRemovedItemsDao(database: AppDatabase): RemovedItemsDao {
        return database.removedItemsDao()
    }
}