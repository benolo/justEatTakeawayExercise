package com.justeattakeawayexercise.ui.restaurants.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RestaurantActivityViewModel : ViewModel() {

    val loaderInteractionRequestedLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val showRestaurantFragmentLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val onExitRequestedLiveData: MutableLiveData<Boolean> = MutableLiveData()

    private var isStarted = false

    fun start() {
        if (!isStarted) {
            isStarted = true
            Log.d("benben", "activity viewmodel started")
            showRestaurantFragmentLiveData.value = true
            onLoaderInteractionRequested(true)
        }
    }

    fun onLoaderInteractionRequested(show: Boolean) {
        Log.d("benben", "onloader interaction called in activity viewmodel with $show")
        loaderInteractionRequestedLiveData.value = show
    }

    fun onBackPressed() {
        onExitRequestedLiveData.value = true
    }
}