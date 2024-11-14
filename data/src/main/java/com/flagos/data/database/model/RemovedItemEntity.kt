package com.flagos.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "removed_items")
data class RemovedItemEntity(
    @PrimaryKey val id: String
)