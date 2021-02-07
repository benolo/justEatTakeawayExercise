package com.justeattakeawayexercise.ui.restaurants.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.justeattakeawayexercise.R
import com.justeattakeawayexercise.ui.restaurants.model.RestaurantItem
import com.justeattakeawayexercise.ui.restaurants.model.RestaurantItemClickListener
import com.squareup.picasso.Picasso


class RestaurantItemAdapter(
    private var restaurantItemList: List<RestaurantItem>,
    private val restaurantItemClickListener: RestaurantItemClickListener
) :RecyclerView.Adapter<RestaurantItemRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantItemRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.restaurant_list_item, parent, false)

        return RestaurantItemRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holderRestaurant: RestaurantItemRecyclerViewHolder, position: Int) {
        val item = restaurantItemList[position]

        setCoverageImage(holderRestaurant.sourceImage, Uri.parse(item.coverageImageUri))

        holderRestaurant.name.text = item.restaurantName

        holderRestaurant.minimumOrder.text = item.minimumOrder

        holderRestaurant.openState.let {
            it.text = setOpeningStateText(it.context, item.openingState)
        }

        holderRestaurant.favoriteState.setImageResource(
            if(item.openingState)  {
                R.drawable.vec_favorite_selected
            }
            else{
                R.drawable.vec_favorite_unselected
            })

        holderRestaurant.favoriteState.setOnClickListener {
            restaurantItemClickListener.onItemFavoriteButtonClicked(position)
        }

        holderRestaurant.itemView.setOnClickListener {
            // Needed for deeper navigation
        }
    }

    private fun setCoverageImage(view: ImageView, imageUri: Uri) {
        val context = view.context
        Picasso.with(context).load(imageUri).placeholder(R.drawable.image_placeholder)
    }

    private fun setOpeningStateText(context: Context, openingState: Boolean): String {
        val openingStateResId =
            if(openingState)  R.string.open_state_opened
            else R.string.open_state_opened

        return context.getString(openingStateResId)
    }

    override fun getItemCount() = restaurantItemList.size

}