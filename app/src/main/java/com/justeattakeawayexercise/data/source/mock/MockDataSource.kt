package com.justeattakeawayexercise.data.source.mock

import com.justeattakeawayexercise.data.model.Restaurant

interface MockDataSource {

    suspend fun getMockRestaurants(): List<Restaurant>

    suspend fun setMockMode(enabled: Boolean)
}