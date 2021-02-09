package com.justeattakeawayexercise.data

import com.justeattakeawayexercise.data.database.RestaurantDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MockModeProvider(private val database: RestaurantDatabase) {

    suspend fun isMockMode(): Boolean {
        return withContext(Dispatchers.IO) {
            return@withContext database.restaurantDao().isMockMode()?.enabled ?: false
        }
    }
}