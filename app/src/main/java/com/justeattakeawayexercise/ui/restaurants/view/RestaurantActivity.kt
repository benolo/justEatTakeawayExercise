package com.justeattakeawayexercise.ui.restaurants.view

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.justeattakeawayexercise.R.id
import com.justeattakeawayexercise.R.layout
import com.justeattakeawayexercise.ui.restaurants.model.ToolbarEntity
import com.justeattakeawayexercise.ui.restaurants.viewmodel.RestaurantActivityViewModel
import kotlinx.android.synthetic.main.restaurant_activity_layout.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class RestaurantActivity : AppCompatActivity() {

    private val viewModel: RestaurantActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.restaurant_activity_layout)

        viewModel.start()
        observe()
    }

    private fun observe() {
        viewModel.toolbarEntityLiveData.observe(this) { initToolbar(it) }
        viewModel.loaderInteractionRequestedLiveData.observe(this) { showLoader(it) }
        viewModel.showRestaurantFragmentLiveData.observe(this) { showRestaurantFragment() }
    }

    private fun initToolbar(toolbarEntity: ToolbarEntity) {
        btn_toolbarBackButton.setImageResource(toolbarEntity.buttonDrawable)
        val toolbarClickListener = View.OnClickListener {
            viewModel.onToolbarButtonClicked()
        }
        btn_toolbarBackButton.setOnClickListener(toolbarClickListener)

        tv_toolbarTitle.setSingleLine()
        tv_toolbarTitle.ellipsize = TextUtils.TruncateAt.END
        tv_toolbarTitle.text = toolbarEntity.title
    }

    private fun showRestaurantFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(id.content_container, RestaurantFragment.newInstance(), RestaurantFragment.TAG)
            .commit()
    }

    private fun showLoader(show: Boolean) {
        progressBar.visibility =
            if (show) View.VISIBLE
            else View.GONE
    }
}