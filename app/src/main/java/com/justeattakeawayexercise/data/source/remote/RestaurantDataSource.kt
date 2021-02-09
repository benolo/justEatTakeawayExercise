package com.justeattakeawayexercise.data.source.remote

import com.justeattakeawayexercise.data.model.Restaurant

/**
 *  Restaurants data source api
 */
interface RestaurantDataSource {

    suspend fun getRestaurants(): List<Restaurant>
}