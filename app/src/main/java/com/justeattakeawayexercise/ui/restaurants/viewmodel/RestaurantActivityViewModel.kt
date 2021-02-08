package com.justeattakeawayexercise.ui.restaurants.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RestaurantActivityViewModel : ViewModel() {

    val showRestaurantFragmentLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val onExitRequestedLiveData: MutableLiveData<Boolean> = MutableLiveData()

    private var isStarted = false

    fun start() {
        if (!isStarted) {
            isStarted = true
            showRestaurantFragmentLiveData.value = true
        }
    }

    fun onBackPressed() {
        onExitRequestedLiveData.value = true
    }
}