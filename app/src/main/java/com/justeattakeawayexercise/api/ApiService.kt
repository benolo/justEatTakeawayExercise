package com.justeattakeawayexercise.api

import com.justeattakeawayexercise.BuildConfig
import com.justeattakeawayexercise.api.model.RestaurantsInfoRaw
import retrofit2.http.GET

interface ApiService {

    @GET(BuildConfig.API_URL)
    suspend fun getIt(): RestaurantsInfoRaw
}