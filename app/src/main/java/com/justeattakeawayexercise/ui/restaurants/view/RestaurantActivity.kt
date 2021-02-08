package com.justeattakeawayexercise.ui.restaurants.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.justeattakeawayexercise.R.id
import com.justeattakeawayexercise.R.layout
import com.justeattakeawayexercise.ui.restaurants.viewmodel.RestaurantActivityViewModel
import kotlinx.android.synthetic.main.restaurant_activity_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RestaurantActivity : AppCompatActivity() {

    private val viewModel: RestaurantActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.restaurant_activity_layout)

        viewModel.start()
        observe()
    }

    private fun observe() {
        viewModel.showRestaurantFragmentLiveData.observe(this) { showRestaurantFragment(it) }
        viewModel.onExitRequestedLiveData.observe(this) { exit(it) }
        viewModel.loaderInteractionRequestedLiveData.observe(this) { showLoader(it) }
    }

    private fun showRestaurantFragment(show: Boolean) {
        Log.d("benben", "showing the fragment")
        if (show) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    id.content_container,
                    RestaurantFragment.newInstance(),
                    RestaurantFragment.TAG
                )
                .commit()
        }
    }

    override fun onBackPressed() = viewModel.onBackPressed()

    private fun exit(shouldExit: Boolean) {
        Log.d("benben", "on exit requested with value: $shouldExit")
        if (shouldExit) {
            Log.d("benben", "bye bye")
            finish()
        }
    }

    private fun showLoader(show: Boolean) {
        Log.d("benben", "in showLoader with value show=$show")
        progressBar.visibility =
            if (show) View.VISIBLE
            else View.GONE
    }
}