package com.justeattakeawayexercise.ui.restaurants.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.observe
import com.justeattakeawayexercise.R.id
import com.justeattakeawayexercise.R.layout
import com.justeattakeawayexercise.ui.restaurants.viewmodel.RestaurantActivityViewModel
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
    }

    private fun showRestaurantFragment(show: Boolean) {
        if (show) {
            val f = supportFragmentManager.findFragmentByTag(RestaurantFragment.TAG)
            if (f == null) {
                supportFragmentManager
                    .beginTransaction()
                    .replace(
                        id.content_container,
                        RestaurantFragment.newInstance(),
                        RestaurantFragment.TAG
                    )
                    .commit()
            }
            else {
                supportFragmentManager.popBackStack(RestaurantFragment.TAG,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }
        }
    }

    override fun onBackPressed() = viewModel.onBackPressed()

    private fun exit(shouldExit: Boolean) {
        if (shouldExit) {
            finish()
        }
    }
}