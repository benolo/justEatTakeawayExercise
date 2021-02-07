package com.justeattakeawayexercise.data

import com.justeattakeawayexercise.data.model.Restaurant
import com.justeattakeawayexercise.data.source.MyDataSource

class MyRepositoryImpl(private val dataSource: MyDataSource) : MyRepository {

    override suspend fun getData(): List<Restaurant> {
        return dataSource.getData()
    }
}