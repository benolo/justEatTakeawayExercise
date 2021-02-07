package com.justeattakeawayexercise.ui.restaurants.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.justeattakeawayexercise.R
import com.justeattakeawayexercise.provider.ResourceProvider
import com.justeattakeawayexercise.ui.restaurants.model.ToolbarEntity

class RestaurantActivityViewModel(private val resourceProvider: ResourceProvider): ViewModel() {

    val toolbarEntityLiveData: MutableLiveData<ToolbarEntity> = MutableLiveData()
    val loaderInteractionRequestedLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val showRestaurantFragmentLiveData: MutableLiveData<Unit> = MutableLiveData()

    private var isStarted = false

    fun start() {
        if(!isStarted) {
            setToolbar()
            showRestaurantFragmentLiveData.value = null
            loaderInteractionRequestedLiveData.value = true
        }
    }

    private fun setToolbar() {
        toolbarEntityLiveData.value =
            ToolbarEntity(resourceProvider.getString(R.string.toolbarTitle), R.drawable.vect_back)
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun onToolbarButtonClicked() {

    }
}