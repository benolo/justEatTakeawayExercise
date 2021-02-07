package com.justeattakeawayexercise.ui.restaurants.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.justeattakeawayexercise.R
import com.justeattakeawayexercise.ui.restaurants.viewmodel.RestaurantFragmentViewModel


class RestaurantFragment: Fragment() {

    private val viewModel: RestaurantFragmentViewModel by viewModels()

    companion object {

        const val TAG = "RestaurantFragment"

        fun newInstance() = RestaurantFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.restaurant_fragment_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun observe() {

    }
}