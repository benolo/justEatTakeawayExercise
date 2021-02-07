package com.justeattakeawayexercise.data.source.remote

import com.justeattakeawayexercise.api.ApiService
import com.justeattakeawayexercise.data.model.Restaurant
import com.justeattakeawayexercise.data.source.MyDataSource

class MyRemoteDataSource(private val apiService: ApiService, private val mapper: Mapper) :
    MyDataSource {

    override suspend fun getData(): List<Restaurant> {
        return mapper.apply(apiService.getIt())
    }
}