package com.justeattakeawayexercise.ui.restaurants.viewmodel

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

private const val TAG = "RestaurantFragmentViewModel"

class RestaurantFragmentViewModel(
    private val repository: RepositoryImpl,
    private val restaurantsMapper: RestaurantMapper
) : ViewModel() {

    val onRestaurantsLoadedLiveData: MutableLiveData<List<RestaurantItem>> = MutableLiveData()
    val showNoResultsLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val onItemChangedLiveData: MutableLiveData<Int> = MutableLiveData()
    val onLoaderInteractionRequestedLiveData: MutableLiveData<Boolean> = MutableLiveData()

    private var isStarted = false

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->

        viewModelScope.launch(Dispatchers.Main) {
            showNoResultsLiveData.value = true
            Timber.tag(TAG).e("Coroutine exception catch error[${throwable}]")
            throwable.printStackTrace()
        }
    }

    fun start() {
        if (!isStarted) {
            isStarted = true
            viewModelScope.launch(exceptionHandler + Dispatchers.Main) {
                onLoaderInteractionRequestedLiveData.value = true
                loadRestaurants()
            }
        } else {
            val list = onRestaurantsLoadedLiveData.value
            onRestaurantsLoadedLiveData.value = list
        }
    }

    private suspend fun fetchFavorites(): List<Int> {
        return withContext(Dispatchers.IO) {
            return@withContext repository.getFavorites()
        }
    }

    private suspend fun fetchRestaurants(): List<Restaurant> {
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
        val allRestaurants = fetchRestaurants()
        val allFavorites = fetchFavorites()
        val resultList = restaurantsMapper.apply(allFavorites, allRestaurants)

        withContext(Dispatchers.Main) {
            if(resultList.isEmpty())  showNoResultsLiveData.value = true
            else onRestaurantsLoadedLiveData.value = resultList
            onLoaderInteractionRequestedLiveData.value = false
        }
    }

    fun onFavoriteClicked(position: Int) {
        val itemClicked = onRestaurantsLoadedLiveData.value?.get(position)
        if (itemClicked != null) {
            viewModelScope.launch(Dispatchers.Main) {
                // Need to delete it from favorites
                if (itemClicked.isFavorite) {
                    deleteRestaurant(itemClicked.restaurantId)
                }
                // Need to insert it as favorite
                else {
                    insertRestaurant(itemClicked.restaurantId)
                }
                itemClicked.isFavorite = !itemClicked.isFavorite
                onItemChangedLiveData.value = position
            }
        }
    }

}