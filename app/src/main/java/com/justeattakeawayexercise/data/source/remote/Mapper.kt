package com.justeattakeawayexercise.data.source.remote

import androidx.arch.core.util.Function
import com.justeattakeawayexercise.api.model.RestaurantsInfoRaw
import com.justeattakeawayexercise.data.model.Restaurant

private const val DEFAULT_RESTAURANT_NAME = "Restaurant"

class Mapper : Function<RestaurantsInfoRaw, List<Restaurant>> {

    override fun apply(raw: RestaurantsInfoRaw?): List<Restaurant> {
        val resultList = mutableListOf<Restaurant>()

        if (raw?.restaurants != null) {
            raw.restaurants.forEach {
                val restaurantId = it.id ?: -1
                val restaurantName = it.name ?: DEFAULT_RESTAURANT_NAME
                val coverageImageUrl = it.coverImageUrl
                val minimumOrderRequired = it.minimumOrder ?: 0
                val isRestaurantOpen = it.isOpen ?: false
                resultList.add(
                    Restaurant(
                        restaurantId, restaurantName, isRestaurantOpen,
                        coverageImageUrl, minimumOrderRequired
                    )
                )
            }
        }

        return resultList
    }
}