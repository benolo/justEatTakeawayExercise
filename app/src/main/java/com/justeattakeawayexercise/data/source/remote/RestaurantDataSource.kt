package com.justeattakeawayexercise.data.source.remote

import com.justeattakeawayexercise.data.model.Restaurant

interface RestaurantDataSource {

    suspend fun getRestaurants(): List<Restaurant>
}