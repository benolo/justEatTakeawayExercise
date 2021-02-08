package com.justeattakeawayexercise.ui.restaurants.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justeattakeawayexercise.data.RepositoryImpl
import com.justeattakeawayexercise.data.model.Restaurant
import com.justeattakeawayexercise.ui.restaurants.mapper.RestaurantMapper
import com.justeattakeawayexercise.ui.restaurants.model.RestaurantItem
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

private const val TAG = "MainViewModel"

class RestaurantFragmentViewModel(
    private val repository: RepositoryImpl,
    private val restaurantsMapper: RestaurantMapper
) : ViewModel() {

    val onRestaurantsLoadedLiveData: MutableLiveData<List<RestaurantItem>> = MutableLiveData()
    val onItemChangedLiveData: MutableLiveData<Int> = MutableLiveData()
    val onLoaderInteractionRequestedLiveData: MutableLiveData<Boolean> = MutableLiveData()

    private var isStarted = false

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.tag(TAG).e("Coroutine exception catch error[${throwable}]")
        throwable.printStackTrace()
    }

    fun start() {
        Log.d("benben", "frag view model start called")
        if (!isStarted) {
            isStarted = true
            viewModelScope.launch(exceptionHandler + Dispatchers.Main) {
                Log.d("benben", "starting coroutine to load restaurants")
                onLoaderInteractionRequestedLiveData.value = true
                loadRestaurants()
            }
        }
    }

    private suspend fun fetchFavorites() :List<Int> {
        Log.d("benben", "fetch favorites invoked")
       return withContext(Dispatchers.IO) {
            return@withContext repository.getFavorites()
        }
    }

    private suspend fun fetchRestaurants() : List<Restaurant>{
        Log.d("benben", "fetch restaurants invoked")
        return withContext(Dispatchers.IO) {
            return@withContext repository.getData()
        }
    }

    private suspend fun insertRestaurant(restaurantId: Int) =
        withContext(Dispatchers.IO) {
            return@withContext repository.insertRestaurant(restaurantId)
        }

    private suspend fun deleteRestaurant(restaurantId: Int) =
        withContext(Dispatchers.IO) {
            return@withContext repository.deleteRestaurant(restaurantId)
        }

    private suspend fun loadRestaurants() {
        Log.d("benben", "load restaurants invoked")
        val allRestaurants = fetchRestaurants()
        Log.d("benben", "all restaurants are: $allRestaurants")
        val allFavorites = fetchFavorites()
        Log.d("benben", "all favorites are: $allFavorites")
        val resultList = restaurantsMapper.apply(allFavorites, allRestaurants)

        withContext(Dispatchers.Main) {
            Log.d("benben", "dispatching data: $resultList")
            onLoaderInteractionRequestedLiveData.value = false
            onRestaurantsLoadedLiveData.value = resultList
        }
    }

    fun onFavoriteClicked(position: Int) {
        val itemClicked = onRestaurantsLoadedLiveData.value?.get(position)
        if (itemClicked != null) {
            viewModelScope.launch(Dispatchers.Main) {
                // Need to delete it from favorites
                if (itemClicked.isFavorite) {
                    insertRestaurant(itemClicked.restaurantId)
                }
                // Need to insert it as favorite
                else {
                    deleteRestaurant(itemClicked.restaurantId)
                }
                onItemChangedLiveData.value = position
            }
        }
    }

}