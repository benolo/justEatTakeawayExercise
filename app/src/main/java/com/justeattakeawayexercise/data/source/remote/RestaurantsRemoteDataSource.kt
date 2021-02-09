package com.justeattakeawayexercise.data.source.remote

import com.justeattakeawayexercise.api.ApiService
import com.justeattakeawayexercise.data.model.Restaurant

class RestaurantsRemoteDataSource(private val apiService: ApiService, private val mapper: Mapper) :
    RestaurantDataSource {

    /**
     * Get all restaurants from server
     *
     * @return A list of restaurants fetched or empty list if no restaurants fetched at all
     */
    override suspend fun getRestaurants(): List<Restaurant> {
        return mapper.apply(apiService.getRestaurants())
    }
}