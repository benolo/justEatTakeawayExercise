package com.justeattakeawayexercise.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurant")
data class RestaurantEntity(
    @PrimaryKey
    val id: Int
)