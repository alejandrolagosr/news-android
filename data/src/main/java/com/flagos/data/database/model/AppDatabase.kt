package com.flagos.data.database.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.flagos.data.database.NewsDao

@Database(entities = [NewsEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}