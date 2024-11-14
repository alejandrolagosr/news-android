package com.flagos.data.mapper

import com.flagos.data.database.model.NewsEntity
import com.flagos.data.networking.model.NewsResponse
import com.flagos.data.util.toRelativeTime
import com.flagos.domain.model.News

fun NewsResponse.toDomain(): News =
    News(
        id = id,
        title = title,
        author = author,
        timestamp = createdAt.toRelativeTime(),
        url = url.orEmpty(),
    )

fun News.toEntity(): NewsEntity = NewsEntity(
    id = this.id,
    title = this.title,
    author = this.author,
    url = this.url,
    timestamp = System.currentTimeMillis()
)

fun NewsEntity.toDomain(): News = News(
    id = this.id,
    title = this.title,
    author = this.author,
    url = this.url,
    timestamp = this.timestamp.toString()
)
