package com.justeattakeawayexercise.api.model

import com.google.gson.annotations.SerializedName

/**
 * Raw data representing the item scheme from the server
 */
data class RestaurantsInfoRaw(
    @SerializedName("restaurants")
    val restaurants: List<RestaurantRaw>?
) {
    data class RestaurantRaw(

        @SerializedName("coverImageUrl")
        val coverImageUrl: String?,

        @SerializedName("id")
        val id: Int?,

        @SerializedName("minimumOrder")
        val minimumOrder: Int?,

        @SerializedName("name")
        val name: String?,

        @SerializedName("open")
        val isOpen: Boolean?
    )
}