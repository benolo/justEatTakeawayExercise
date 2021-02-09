package com.justeattakeawayexercise.data.source.mock

import com.justeattakeawayexercise.data.database.RestaurantDatabase
import com.justeattakeawayexercise.data.database.model.MockEntity
import com.justeattakeawayexercise.data.model.Restaurant
import com.justeattakeawayexercise.data.source.remote.Mapper
import com.justeattakeawayexercise.utils.providers.AssetsParser

const val MOCK_DATA_FILE_NAME = "mock_data.json"
const val ENTITY_ID = 1

class MockLocalDataSource(private val assetsParser: AssetsParser, private val mapper: Mapper,
                          private val database: RestaurantDatabase) : MockDataSource {

    /**
     * Retrieve the restaurants from a file stored in app's assets
     *
     * @return A list of [Restaurant]s
     */
    override suspend fun getMockRestaurants(): List<Restaurant> {
        return mapper.apply(assetsParser.read(MOCK_DATA_FILE_NAME))
    }

    /**
     * Persist mock mode (defining if data will be fetched from server or from app's assets)
     * state to [RestaurantDatabase] under a table defined in [MockEntity]
     *
     * @param enabled The new state of mock mode
     */
    override suspend fun setMockMode(enabled: Boolean) {
        database.restaurantDao().setMockMode(MockEntity(ENTITY_ID, enabled))
    }

}