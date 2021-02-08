package com.justeattakeawayexercise.ui.restaurants.model

data class RestaurantItem(

    val restaurantId: Int,

    val coverageImageUri: String?,

    val restaurantName: String,

    val minimumOrder: Int,

    val openingState: Boolean,

    val isFavorite: Boolean
)