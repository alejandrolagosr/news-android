package com.flagos.data.database.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.flagos.data.database.NewsDao
import com.flagos.data.database.RemovedItemsDao

@Database(entities = [NewsEntity::class, RemovedItemEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
    abstract fun removedItemsDao(): RemovedItemsDao
}