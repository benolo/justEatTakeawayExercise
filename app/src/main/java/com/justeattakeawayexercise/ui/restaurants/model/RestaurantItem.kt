package com.justeattakeawayexercise.ui.restaurants.model

/**
 * Ui data object representation
 */
data class RestaurantItem(

    val restaurantId: Int,

    val coverageImageUri: String?,

    val restaurantName: String,

    val minimumOrder: Int,

    val openingState: Boolean,

    var isFavorite: Boolean
)