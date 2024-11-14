package com.flagos.data.mapper

import com.flagos.data.networking.model.NewsResponse
import com.flagos.data.util.toRelativeTime
import com.flagos.domain.model.News

fun NewsResponse.toDomain(): News =
    News(
        id = id,
        title = title,
        author = author,
        relativeTime = createdAt.toRelativeTime(),
        url = url.orEmpty(),
    )
