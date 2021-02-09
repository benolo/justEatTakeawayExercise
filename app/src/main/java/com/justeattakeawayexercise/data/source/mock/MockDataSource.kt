package com.justeattakeawayexercise.data.source.mock

import com.justeattakeawayexercise.data.model.Restaurant

/**
 * Mock restaurants data source api
 */
interface MockDataSource {

    suspend fun getMockRestaurants(): List<Restaurant>

    suspend fun setMockMode(enabled: Boolean)
}