package com.justeattakeawayexercise.data

import com.justeattakeawayexercise.data.model.Restaurant

interface Repository {

    suspend fun getData(): List<Restaurant>

    suspend fun getFavorites(): List<Int>

    suspend fun isFavoriteRestaurant(restaurantId: Int): Boolean

    suspend fun insertRestaurant(restaurantId: Int)

    suspend fun deleteRestaurant(restaurantId: Int)
}