package com.flagos.data.networking.model

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("story_id")
    val id: String,
    @SerializedName("story_title")
    val title: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("story_url")
    val url: String?,
)
