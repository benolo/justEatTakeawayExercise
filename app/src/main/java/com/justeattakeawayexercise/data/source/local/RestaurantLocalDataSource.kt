package com.justeattakeawayexercise.data.source.local

import com.justeattakeawayexercise.data.database.RestaurantDatabase
import com.justeattakeawayexercise.data.database.model.RestaurantEntity

class RestaurantLocalDataSource(private val database: RestaurantDatabase): FavoritesDataSource {

    /**
     * Fetch all favorites restaurants from [RestaurantDatabase]
     *
     * @return A list of all [Int] with all favorite restaurants ids or empty list if no
     * favorites are set
     */
    override suspend fun getFavorites(): List<Int> {
        val targetList = mutableListOf<Int>()
        return database.restaurantDao().getAll().mapTo(targetList) { it.id}
    }

    /**
     * Query whether a restaurant is stored as favorite
     *
     * @param restaurantId The restaurant id to query with
     * @return True if the restaurant is stored as favorite, false otherwise
     *
     */
    override suspend fun isFavoriteRestaurant(restaurantId: Int): Boolean {
        return database.restaurantDao().getRestaurantById(restaurantId) != null
    }

    /**
     * Store a restaurant as favorite
     *
     * @param restaurantId The id of the restaurant to set as favorite
     *
     */
    override suspend fun insertRestaurant(restaurantId: Int) {
        return database.restaurantDao().insert(RestaurantEntity(restaurantId))
    }

    /**
     * Remove a restaurant from favorites
     *
     * @param restaurantId The id of the restaurant to remove
     *
     */
    override suspend fun deleteRestaurant(restaurantId: Int) {
        return database.restaurantDao().delete(RestaurantEntity(restaurantId))
    }
}