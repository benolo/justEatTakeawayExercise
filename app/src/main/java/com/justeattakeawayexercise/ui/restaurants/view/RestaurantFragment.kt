package com.justeattakeawayexercise.ui.restaurants.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.justeattakeawayexercise.R
import com.justeattakeawayexercise.ui.restaurants.adapter.RestaurantAdapter
import com.justeattakeawayexercise.ui.restaurants.model.RestaurantItem
import com.justeattakeawayexercise.ui.restaurants.model.RestaurantItemClickListener
import com.justeattakeawayexercise.ui.restaurants.viewmodel.RestaurantFragmentViewModel
import kotlinx.android.synthetic.main.restaurant_fragment_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class RestaurantFragment : Fragment(), RestaurantItemClickListener {

    private val viewModel: RestaurantFragmentViewModel by viewModel()

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: RestaurantAdapter
    private lateinit var menu: Menu

    companion object {

        const val TAG = "RestaurantFragment"

        fun newInstance() = RestaurantFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.restaurant_fragment_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.start()
        observe()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        this.menu = menu
        viewModel.onMenuItemLoaded()
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mock_request -> {
                viewModel.onMockModeMenuClicked()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun observe() {
        viewModel.onLoaderInteractionRequestedLiveData.observe(viewLifecycleOwner) { showLoader(it) }
        viewModel.onRestaurantsLoadedLiveData.observe(viewLifecycleOwner) { showRestaurantList(it) }
        viewModel.onItemChangedLiveData.observe(viewLifecycleOwner) { updateItemAtPosition(it) }
        viewModel.showNoResultsLiveData.observe(viewLifecycleOwner) { showEmptyState(it) }
        viewModel.onMenuItemTextChangedLiveData.observe(viewLifecycleOwner) { updateMenuText(it) }
    }

    private fun updateMenuText(menuTestResId: Int) {
        if (::menu.isInitialized) {
            menu.findItem(R.id.mock_request)?.let {
                it.title = getString(menuTestResId)
            }
        }
    }

    private fun updateItemAtPosition(position: Int) = adapter.notifyItemChanged(position)

    private fun initViews() {
        layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        restaurantsRecycler.layoutManager = layoutManager
        restaurantsRecycler.setHasFixedSize(true)
        restaurantsRecycler.itemAnimator?.changeDuration = 0;
        restaurantsRecycler.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun showRestaurantList(restaurantsList: List<RestaurantItem>) {
        if (::adapter.isInitialized) {
            adapter.setData(restaurantsList)
        } else {
            adapter = RestaurantAdapter(restaurantsList, this)
            restaurantsRecycler.adapter = adapter
        }
    }

    private fun showLoader(show: Boolean) {
        progressBar.visibility =
            if (show) View.VISIBLE
            else View.GONE
    }

    private fun showEmptyState(show: Boolean) {
        if (show) {
            resultsGroup.visibility = View.GONE
            emptyState.visibility = View.VISIBLE
        } else {
            resultsGroup.visibility = View.VISIBLE
            emptyState.visibility = View.GONE
        }
    }

    override fun onItemFavoriteButtonClicked(position: Int) = viewModel.onFavoriteClicked(position)
}