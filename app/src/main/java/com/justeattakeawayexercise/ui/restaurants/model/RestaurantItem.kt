package com.justeattakeawayexercise.ui.restaurants.model

data class RestaurantItem(

    val coverageImageUri: String,

    val restaurantName: String,

    val minimumOrder: String,

    val openingState: Boolean,

    val isFavorite: Boolean
)