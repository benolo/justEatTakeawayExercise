package com.justeattakeawayexercise.data.database

import androidx.room.*
import com.justeattakeawayexercise.data.database.model.MockEntity
import com.justeattakeawayexercise.data.database.model.RestaurantEntity

@Dao
interface RestaurantDao {

    /**
     * Get all the restaurants as list of [RestaurantEntity]
     */
    @Query("SELECT * FROM restaurant")
    fun getAll(): List<RestaurantEntity>


    /**
     * Get a single restaurant by its id
     *
     * @param restaurantId Restaurant id to query with
     */
    @Query("SELECT * FROM restaurant WHERE id = :restaurantId")
    fun getRestaurantById(restaurantId: Int): RestaurantEntity?


    /**
     * Return the entity describing mock mode
     * ### Note: The entity id will always be 1 since it acts as state
     */
    @Query("SELECT * FROM mock WHERE id = 1")
    fun isMockMode(): MockEntity?


    /**
     * Set a new state to mock mode
     *
     * @param entity the [MockEntity] holding the new mock mode state value
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setMockMode(entity: MockEntity)


    /**
     * Insert, or replace if exising, a [RestaurantEntity] as favorite restaurant
     *
     * @param entity The [RestaurantEntity] to store as favorite
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: RestaurantEntity)

    /**
     * Delete a [RestaurantEntity] from favorite restaurants
     *
     * @param entity The [RestaurantEntity] to remove from favorites
     */
    @Delete
    fun delete(entity: RestaurantEntity)
}