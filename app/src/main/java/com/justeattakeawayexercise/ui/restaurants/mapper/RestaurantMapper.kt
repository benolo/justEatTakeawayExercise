package com.justeattakeawayexercise.ui.restaurants.mapper

import com.justeattakeawayexercise.data.model.Restaurant
import com.justeattakeawayexercise.ui.restaurants.model.RestaurantItem
import java.util.function.BiFunction

class RestaurantMapper : BiFunction<List<Int>, List<Restaurant>, List<RestaurantItem>> {

    override fun apply(favorites: List<Int>, allRestaurants: List<Restaurant>)
            : List<RestaurantItem> {

        val restaurantItemList = mutableListOf<RestaurantItem>()

        allRestaurants.forEach {
            restaurantItemList.add(
                RestaurantItem(
                    it.id, it.coverImageUrl, it.name, it.minimumOrder, it.isOpen,
                    favorites.contains(it.id)
                )
            )
        }
        return restaurantItemList
    }
}