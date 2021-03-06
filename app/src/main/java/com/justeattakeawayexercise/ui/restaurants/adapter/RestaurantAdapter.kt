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


class RestaurantAdapter(
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

        holderRestaurant.minimumOrder.text =
            setMinimumOrderText(holderRestaurant.minimumOrder.context, item.minimumOrder)

        holderRestaurant.openState.let {
            it.text = setOpeningStateText(it.context, item.openingState)
            it.isEnabled = item.openingState
        }

        holderRestaurant.favoriteState.isSelected = item.isFavorite

        holderRestaurant.favoriteState.setOnClickListener {
            restaurantItemClickListener.onItemFavoriteButtonClicked(position)
        }

    }

    private fun setCoverageImage(view: ImageView, imageUri: Uri) {
        val context = view.context
        Picasso.with(context)
            .load(imageUri)
            .placeholder(R.drawable.image_placeholder)
            .fit()
            .centerInside()
            .into(view)
    }

    private fun setOpeningStateText(context: Context, openingState: Boolean): String {
        val openingStateResId =
            if(openingState)  R.string.open_state_opened
            else R.string.open_state_closed

        return context.getString(openingStateResId)
    }

    private fun setMinimumOrderText(context: Context, minimumOrderAmount: Int): String {
        return String.format(context.getString(R.string.minimum_order_pattern), minimumOrderAmount)
    }

    fun setData(restaurantsList: List<RestaurantItem>) {
        restaurantItemList = restaurantsList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return restaurantItemList.size
    }

}