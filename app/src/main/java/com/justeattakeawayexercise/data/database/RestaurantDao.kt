package com.justeattakeawayexercise.data.database

import androidx.room.*
import com.justeattakeawayexercise.data.database.model.MockEntity
import com.justeattakeawayexercise.data.database.model.RestaurantEntity

@Dao
interface RestaurantDao {

    @Query("SELECT * FROM restaurant")
    fun getAll(): List<RestaurantEntity>

    @Query("SELECT * FROM restaurant WHERE id = :restaurantId")
    fun getRestaurantById(restaurantId: Int): RestaurantEntity?

    @Query("SELECT * FROM mock WHERE id = 1")
    fun isMockMode(): MockEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setMockMode(entity: MockEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: RestaurantEntity)

    @Delete
    fun delete(entity: RestaurantEntity)
}