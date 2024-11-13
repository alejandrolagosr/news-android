package com.flagos.data.networking

import com.flagos.data.networking.model.HitsResponse
import retrofit2.Response
import retrofit2.http.GET

interface NewsApi {

    @GET("search_by_date?query=mobile")
    suspend fun getNewsByDate(): Response<HitsResponse>
}