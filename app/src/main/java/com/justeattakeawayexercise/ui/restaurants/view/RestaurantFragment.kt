package com.justeattakeawayexercise.ui.restaurants.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.justeattakeawayexercise.R
import com.justeattakeawayexercise.ui.restaurants.adapter.RestaurantAdapter
import com.justeattakeawayexercise.ui.restaurants.model.RestaurantItem
import com.justeattakeawayexercise.ui.restaurants.model.RestaurantItemClickListener
import com.justeattakeawayexercise.ui.restaurants.viewmodel.RestaurantActivityViewModel
import com.justeattakeawayexercise.ui.restaurants.viewmodel.RestaurantFragmentViewModel
import kotlinx.android.synthetic.main.restaurant_fragment_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class RestaurantFragment: Fragment(), RestaurantItemClickListener {

    private val activityViewModel: RestaurantActivityViewModel by viewModel()

    private val viewModel: RestaurantFragmentViewModel by viewModel()

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: RestaurantAdapter

    companion object {

        const val TAG = "RestaurantFragment"

        fun newInstance() = RestaurantFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        Log.d("benben", "fragment oncreate view called")
        return inflater.inflate(R.layout.restaurant_fragment_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d("benben", "fragment onactivity created called")
        super.onActivityCreated(savedInstanceState)
        viewModel.start()
        observe()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("benben", "fragment onViewCreated called")
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun observe() {
        viewModel.onLoaderInteractionRequestedLiveData.observe(viewLifecycleOwner) { activityViewModel.onLoaderInteractionRequested(it)}
        viewModel.onRestaurantsLoadedLiveData.observe(viewLifecycleOwner) { showRestaurantList(it) }
        viewModel.onItemChangedLiveData.observe(viewLifecycleOwner) { updateItemAtPosition(it) }
    }

    private fun updateItemAtPosition(itemPosition: Int) {
        adapter.notifyItemChanged(itemPosition)
    }

    private fun initViews() {
        Log.d("benben", "in init views")
        layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        restaurantsRecycler.layoutManager = layoutManager
        restaurantsRecycler.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    private fun showRestaurantList(restaurantsList: List<RestaurantItem>) {
        Log.d("benben", "show restaurant called")
        if(::adapter.isInitialized) {
            adapter.setData(restaurantsList)
        }
        else {
            adapter = RestaurantAdapter(restaurantsList, this)
            restaurantsRecycler.adapter = adapter
        }
    }

    override fun onItemFavoriteButtonClicked(position: Int) {
        viewModel.onFavoriteClicked(position)
    }
}