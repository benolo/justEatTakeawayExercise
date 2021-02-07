package com.justeattakeawayexercise.ui.restaurants.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.justeattakeawayexercise.R

class RestaurantItemRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val sourceImage: ImageView = itemView.findViewById(R.id.iv_coverageImage)
    val name: TextView = itemView.findViewById(R.id.tv_restaurantName)
    val minimumOrder: TextView = itemView.findViewById(R.id.tv_minimumOrder)
    val openState: TextView = itemView.findViewById(R.id.tv_openingState)
    val favoriteState: ImageView = itemView.findViewById(R.id.iv_favorite)
}