package com.justeattakeawayexercise.data.database

import androidx.room.*
import com.justeattakeawayexercise.data.database.model.RestaurantEntity

@Dao
interface RestaurantDao {

    @Query("SELECT * FROM restaurant")
    fun getAll(): List<RestaurantEntity>

    @Query("SELECT * FROM restaurant WHERE id = :restaurantId")
    fun getRestaurantById(restaurantId: Int): RestaurantEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: RestaurantEntity)

    @Delete
    fun delete(entity: RestaurantEntity)
}