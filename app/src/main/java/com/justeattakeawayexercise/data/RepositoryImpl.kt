package com.justeattakeawayexercise.data

import com.justeattakeawayexercise.data.model.Restaurant
import com.justeattakeawayexercise.data.source.local.RestaurantLocalDataSource
import com.justeattakeawayexercise.data.source.mock.MockLocalDataSource
import com.justeattakeawayexercise.data.source.remote.RestaurantsRemoteDataSource

class RepositoryImpl(
    private val dataSource: RestaurantsRemoteDataSource,
    private val favoritesDataSource: RestaurantLocalDataSource,
    private val mockLocalDataSource: MockLocalDataSource,
    private val mockModeProvider: MockModeProvider
) : Repository {

    override suspend fun getRestaurants(): List<Restaurant> {
        if(mockModeProvider.isMockMode()) {
            return mockLocalDataSource.getMockRestaurants()
        }
        return dataSource.getRestaurants()
    }

    override suspend fun getFavorites(): List<Int> {
        return favoritesDataSource.getFavorites()
    }

    override suspend fun isFavoriteRestaurant(restaurantId: Int): Boolean {
        return favoritesDataSource.isFavoriteRestaurant(restaurantId)
    }

    override suspend fun insertRestaurant(restaurantId: Int) {
        return favoritesDataSource.insertRestaurant(restaurantId)
    }

    override suspend fun deleteRestaurant(restaurantId: Int) {
        return favoritesDataSource.deleteRestaurant(restaurantId)
    }

    override suspend fun setMockMode(enabled: Boolean) {
        mockLocalDataSource.setMockMode(enabled)
    }
}