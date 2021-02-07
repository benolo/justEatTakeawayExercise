package com.justeattakeawayexercise.data.source

import com.justeattakeawayexercise.data.model.Restaurant

interface MyDataSource {

    suspend fun getData(): List<Restaurant>
}