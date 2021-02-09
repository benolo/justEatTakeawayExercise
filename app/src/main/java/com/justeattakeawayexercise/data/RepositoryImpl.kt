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

    /**
     * Get all restaurants as a list of [Restaurant]
     * ### Note: will use proper data source ([RestaurantsRemoteDataSource] or [MockLocalDataSource])
     * if we are in mock mode or not
     */
    override suspend fun getRestaurants(): List<Restaurant> {
        if(mockModeProvider.isMockMode()) {
            return mockLocalDataSource.getMockRestaurants()
        }
        return dataSource.getRestaurants()
    }

    /**
     * Get a list [Int] that describe all favorite restaurants ids
     */
    override suspend fun getFavorites(): List<Int> {
        return favoritesDataSource.getFavorites()
    }

    /**
     * Will check if a given restaurant id is assigned as favorite
     *
     * @param restaurantId the id of the restaurant to check for
     * @return True if restaurant is set as favorite, false otherwise
     */
    override suspend fun isFavoriteRestaurant(restaurantId: Int): Boolean {
        return favoritesDataSource.isFavoriteRestaurant(restaurantId)
    }

    /**
     * Set a restaurant as favorite using database
     *
     * @param restaurantId The id of restaurant to set as favorite
     */
    override suspend fun insertRestaurant(restaurantId: Int) {
        return favoritesDataSource.insertRestaurant(restaurantId)
    }

    /**
     * Remove a restaurant from favorites
     *
     * @param restaurantId The id of restaurant to remove from favorites
     */
    override suspend fun deleteRestaurant(restaurantId: Int) {
        return favoritesDataSource.deleteRestaurant(restaurantId)
    }

    /**
     * Persist mock mode state
     *
     * @param enabled New mock mode state
     */
    override suspend fun setMockMode(enabled: Boolean) {
        mockLocalDataSource.setMockMode(enabled)
    }
}