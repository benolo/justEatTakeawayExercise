package com.justeattakeawayexercise.ui.restaurants.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justeattakeawayexercise.R
import com.justeattakeawayexercise.data.MockModeProvider
import com.justeattakeawayexercise.data.RepositoryImpl
import com.justeattakeawayexercise.data.model.Restaurant
import com.justeattakeawayexercise.ui.restaurants.mapper.RestaurantMapper
import com.justeattakeawayexercise.ui.restaurants.model.RestaurantItem
import kotlinx.coroutines.*
import timber.log.Timber

private const val TAG = "RestaurantFragmentViewModel"

class RestaurantFragmentViewModel(
    private val repository: RepositoryImpl,
    private val restaurantsMapper: RestaurantMapper,
    private val mockModeProvider: MockModeProvider
) : ViewModel() {

    val onRestaurantsLoadedLiveData: MutableLiveData<List<RestaurantItem>> = MutableLiveData()
    val showNoResultsLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val onItemChangedLiveData: MutableLiveData<Int> = MutableLiveData()
    val onLoaderInteractionRequestedLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val onMenuItemTextChangedLiveData: MutableLiveData<Int> = MutableLiveData()

    private var isStarted = false
    private var isMockMode = false

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
            viewModelScope.launch(exceptionHandler + Dispatchers.Main) {
                dispatchRestaurants(list)
            }
        }
    }

    private suspend fun fetchFavorites(): List<Int> {
        return withContext(Dispatchers.IO) {
            return@withContext repository.getFavorites()
        }
    }

    private suspend fun fetchRestaurants(): List<Restaurant> {
        return withContext(Dispatchers.IO) {
            return@withContext repository.getRestaurants()
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
        dispatchRestaurants(resultList)
    }

    private suspend fun dispatchRestaurants(restaurantList: List<RestaurantItem>?) {
        withContext(Dispatchers.Main) {
            if (restaurantList.isNullOrEmpty()) showNoResultsLiveData.value = true
            else onRestaurantsLoadedLiveData.value = restaurantList
            onLoaderInteractionRequestedLiveData.value = false
        }
    }

    fun onMockModeMenuClicked() {
        isMockMode = !isMockMode
        viewModelScope.launch() {
            withContext(Dispatchers.IO) {
                repository.setMockMode(isMockMode)
                updateMenuText()
            }
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

    fun onMenuItemLoaded() {
        viewModelScope.launch { setMenuUpdatedText() }
    }

    private suspend fun getIsMockMode() =
        withContext(Dispatchers.IO) {
            mockModeProvider.isMockMode()
        }

    private suspend fun setMenuUpdatedText() {
        withContext(Dispatchers.Main) {
            isMockMode = getIsMockMode()
            updateMenuText()
        }
    }

    private suspend fun updateMenuText() {
        withContext(Dispatchers.Main) {
            onMenuItemTextChangedLiveData.value =
                if (isMockMode) R.string.menu_un_mock_request_text
                else R.string.menu_mock_request_text
        }
    }

}