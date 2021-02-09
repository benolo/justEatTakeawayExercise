package com.justeattakeawayexercise.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mock")
data class MockEntity(
    @PrimaryKey
    val id: Int,

    val enabled: Boolean
)