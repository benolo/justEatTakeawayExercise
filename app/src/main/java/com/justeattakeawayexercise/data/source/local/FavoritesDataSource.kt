package com.justeattakeawayexercise.data.source.local

/**
 * Favorite restaurants data source api
 */
interface FavoritesDataSource {

    suspend fun getFavorites(): List<Int>

    suspend fun isFavoriteRestaurant(restaurantId: Int): Boolean

    suspend fun insertRestaurant(restaurantId: Int)

    suspend fun deleteRestaurant(restaurantId: Int)
}