package com.justeattakeawayexercise.ui.restaurants.model

/**
 * Interface to populate user interaction with add/remove to/from favorites
 */
interface RestaurantItemClickListener {

    fun onItemFavoriteButtonClicked(position: Int)

}
