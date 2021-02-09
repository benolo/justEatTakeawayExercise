package com.justeattakeawayexercise.data.source.remote

import androidx.arch.core.util.Function
import com.justeattakeawayexercise.api.model.RestaurantsInfoRaw
import com.justeattakeawayexercise.data.model.Restaurant

/**
 * Mapper class to validate, parse and transform data received before populating
 * it to business logic layer
 *
 * Receive a [RestaurantsInfoRaw] schemed data object and return transformed list of [Restaurant]
 *
 */
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

    /**
     * Assert validity of critical data needed for display
     *
     * @param raw The [RestaurantsInfoRaw] raw object to assert data from
     *
     * @return True if critical data is valid, false otherwise
     *
     * ### Note: false response will result to exclusion of this item from the list
     */
    private fun assertEssentialParams(raw: RestaurantsInfoRaw.RestaurantRaw) =
        !(raw.id == null || raw.name == null || raw.minimumOrder == null)

}