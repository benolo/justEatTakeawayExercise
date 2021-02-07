package com.justeattakeawayexercise.ui.restaurants.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justeattakeawayexercise.data.MyRepository
import com.justeattakeawayexercise.data.model.Restaurant
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "MainViewModel"

class RestaurantFragmentViewModel(private val repository: MyRepository) : ViewModel() {

    val onRestaurantsLoadedLiveData: MutableLiveData<List<Restaurant>> = MutableLiveData()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(TAG, "Coroutine exception catch error[${throwable}]")
        throwable.printStackTrace()
    }

    fun loadRestaurants() {
        viewModelScope.launch(exceptionHandler) {
            val result = repository.getData()
            withContext(Dispatchers.Main) {
                onRestaurantsLoadedLiveData.value = result
            }
        }

    }
}