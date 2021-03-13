package com.patrykkosieradzki.theanimalapp.ui.list.all

import android.view.MenuItem
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.patrykkosieradzki.theanimalapp.R
import com.patrykkosieradzki.theanimalapp.databinding.AllAnimalsFragmentBinding
import com.patrykkosieradzki.theanimalapp.utils.BackNavigationListener
import com.patrykkosieradzki.theanimalapp.utils.BackNavigationResult
import com.patrykkosieradzki.theanimalapp.utils.BaseFragment
import com.patrykkosieradzki.theanimalapp.utils.extensions.navigateTo
import com.patrykkosieradzki.theanimalapp.utils.extensions.removeItemDecorations
import com.patrykkosieradzki.theanimalapp.utils.extensions.valueNN
import kotlinx.coroutines.launch

class AllAnimalsFragment :
    BaseFragment<AllAnimalsViewState, AllAnimalsViewModel, AllAnimalsFragmentBinding>(
        R.layout.all_animals_fragment, AllAnimalsViewModel::class
    ),
    BackNavigationListener {

    private lateinit var adapter: AnimalsAdapter

    override fun setupViews(view: View) {
        super.setupViews(view)
        onBackEvent = { requireActivity().moveTaskToBack(true) }
        adapter = AnimalsAdapter { viewModel.onAnimalItemClicked(it) }
        with(binding) {
            animalsRecyclerView.adapter = this@AllAnimalsFragment.adapter
            bottomAppBar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.more -> {
                        val directions = AllAnimalsFragmentDirections.toSettingsFragment()
                        navigateTo(directions)
                        true
                    }
                    else -> false
                }
            }
        }
        with(viewModel) {
            viewState.observe(viewLifecycleOwner) {
                switchRecyclerViewMode()
            }
            collectedAnimalsEvent.observe(viewLifecycleOwner) {
                lifecycleScope.launch {
                    adapter.submitData(it)
                }
            }
        }
    }

    override fun onToolbarMenuItemClicked(it: MenuItem): Boolean {
        return when (it.itemId) {
            R.id.list_grid_switch -> {
                viewModel.onListGridSwitchClick()
                true
            }
            else -> false
        }
    }

    private fun switchRecyclerViewMode() {
        with(binding) {
            animalsRecyclerView.apply {
                removeItemDecorations()
                when (viewModel.viewState.valueNN.recyclerViewMode) {
                    RecyclerViewMode.LIST -> {
                        (layoutManager as GridLayoutManager).spanCount = 1
                        toolbar.menu.findItem(R.id.list_grid_switch).apply {
                            title = getString(R.string.list_to_grid_switch)
                            setIcon(R.drawable.ic_grid)
                        }
                    }
                    RecyclerViewMode.GRID -> {
                        (layoutManager as GridLayoutManager).spanCount = 2
                        toolbar.menu.findItem(R.id.list_grid_switch).apply {
                            title = getString(R.string.grid_to_list_switch)
                            setIcon(R.drawable.ic_list)
                        }
                    }
                }
                adapter?.notifyItemRangeChanged(0, adapter?.itemCount ?: 0)
            }
        }
    }

    override fun onNavigationResult(result: BackNavigationResult) {
        binding.animalsRecyclerView.run {
            post {
                layoutManager?.scrollToPosition(result.bundle["position"] as Int)
            }
        }
    }
}
