package com.justeattakeawayexercise.data.model

/**
 * Business object defining a restaurant when fetched
 */
data class Restaurant(

    val id: Int,

    val name: String,

    val isOpen: Boolean,

    val coverImageUrl: String?,

    val minimumOrder: Int

)