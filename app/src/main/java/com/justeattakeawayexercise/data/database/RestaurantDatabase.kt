package com.justeattakeawayexercise.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.justeattakeawayexercise.data.database.model.RestaurantEntity

@Database(entities = [RestaurantEntity::class], version = 1)
abstract class RestaurantDatabase : RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDao
}