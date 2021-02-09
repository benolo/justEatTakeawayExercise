package com.justeattakeawayexercise.data.source.remote

import com.justeattakeawayexercise.api.ApiService
import com.justeattakeawayexercise.data.model.Restaurant

class RestaurantsRemoteDataSource(private val apiService: ApiService, private val mapper: Mapper) :
    RestaurantDataSource {

    override suspend fun getRestaurants(): List<Restaurant> {
        return mapper.apply(apiService.getRestaurants())
    }
}