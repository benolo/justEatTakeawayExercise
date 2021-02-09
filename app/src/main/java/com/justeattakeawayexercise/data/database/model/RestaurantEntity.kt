package com.justeattakeawayexercise.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity (table at the end) representing the favorite restaurants
 *
 * @param id the restaurant id to persist as favorite
 *
 */
@Entity(tableName = "restaurant")
data class RestaurantEntity(
    @PrimaryKey
    val id: Int
)