package com.justeattakeawayexercise.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity to be used for mocking the data
 *
 * @param id The id of the entity (will always be the same since the entity act as a state)
 * @param enabled Indicate whether the entity mode is enabled or not
 *
 */
@Entity(tableName = "mock")
data class MockEntity(
    @PrimaryKey
    val id: Int,

    val enabled: Boolean
)