package com.justeattakeawayexercise.data.model

data class Restaurant(

    val id: Int,

    val name: String,

    val isOpen: Boolean,

    val coverImageUrl: String?,

    val minimumOrder: Int

)