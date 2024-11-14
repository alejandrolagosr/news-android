package com.flagos.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey val id: String,
    val title: String,
    val author: String,
    val url: String,
    val timestamp: Long
)