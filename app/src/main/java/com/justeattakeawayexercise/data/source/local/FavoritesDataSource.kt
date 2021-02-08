package com.justeattakeawayexercise.data.source.local

interface FavoritesDataSource {

    suspend fun getFavorites(): List<Int>

    suspend fun isFavoriteRestaurant(restaurantId: Int): Boolean

    suspend fun insertRestaurant(restaurantId: Int)

    suspend fun deleteRestaurant(restaurantId: Int)
}