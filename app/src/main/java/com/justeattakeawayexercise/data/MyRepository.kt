package com.justeattakeawayexercise.data

import com.justeattakeawayexercise.data.model.Restaurant

interface MyRepository {

    suspend fun getData(): List<Restaurant>
}