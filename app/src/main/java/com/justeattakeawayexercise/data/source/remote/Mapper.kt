package com.justeattakeawayexercise.data.source.remote

import androidx.arch.core.util.Function
import com.justeattakeawayexercise.api.model.RestaurantsInfoRaw
import com.justeattakeawayexercise.data.model.Restaurant

class Mapper : Function<RestaurantsInfoRaw, List<Restaurant>> {

    override fun apply(raw: RestaurantsInfoRaw?): List<Restaurant> {
        val resultList = mutableListOf<Restaurant>()

        if (raw?.restaurants != null) {
            raw.restaurants.forEach {
                if(assertEssentialParams(it)) {
                    val restaurantId = it.id
                    val restaurantName = it.name
                    val coverageImageUrl = it.coverImageUrl
                    val minimumOrderRequired = it.minimumOrder
                    val isRestaurantOpen = it.isOpen ?: false
                    resultList.add(
                        Restaurant(
                            restaurantId!!, restaurantName!!, isRestaurantOpen,
                            coverageImageUrl, minimumOrderRequired!!
                        )
                    )
                }
            }
        }
        return resultList
    }

    private fun assertEssentialParams(raw: RestaurantsInfoRaw.RestaurantRaw) =
        !(raw.id == null || raw.name == null || raw.minimumOrder == null)

}