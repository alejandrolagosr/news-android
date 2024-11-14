package com.flagos.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.flagos.data.database.model.RemovedItemEntity

@Dao
interface RemovedItemsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemovedItem(removedItem: RemovedItemEntity)

    @Query("SELECT * FROM removed_items WHERE id = :id LIMIT 1")
    suspend fun isItemRemoved(id: String): RemovedItemEntity?
}