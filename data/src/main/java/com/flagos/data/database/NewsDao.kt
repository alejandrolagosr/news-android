package com.flagos.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.flagos.data.database.model.NewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Query("SELECT * FROM news ORDER BY timestamp DESC")
    fun getNews(): Flow<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: List<NewsEntity>)

    @Query("DELETE FROM news")
    suspend fun clearNews()
}