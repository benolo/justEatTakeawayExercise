package com.justeattakeawayexercise.data.source.local

import com.justeattakeawayexercise.data.database.RestaurantDatabase
import com.justeattakeawayexercise.data.database.model.RestaurantEntity

class RestaurantLocalDataSource(private val database: RestaurantDatabase): FavoritesDataSource {

    override suspend fun getFavorites(): List<Int> {
        val targetList = mutableListOf<Int>()
        return database.restaurantDao().getAll().mapTo(targetList) { it.id}
    }

    override suspend fun isFavoriteRestaurant(restaurantId: Int): Boolean {
        return database.restaurantDao().getRestaurantById(restaurantId) != null
    }

    override suspend fun insertRestaurant(restaurantId: Int) {
        return database.restaurantDao().insert(RestaurantEntity(restaurantId))
    }

    override suspend fun deleteRestaurant(restaurantId: Int) {
        return database.restaurantDao().delete(RestaurantEntity(restaurantId))
    }
}