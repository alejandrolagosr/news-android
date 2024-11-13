package com.flagos.data.networking.model

import com.google.gson.annotations.SerializedName

data class HitsResponse(
    @SerializedName("hits")
    val hits: List<NewsResponse>
)
