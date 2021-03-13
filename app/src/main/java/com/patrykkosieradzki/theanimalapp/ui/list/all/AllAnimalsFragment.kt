package com.patrykkosieradzki.theanimalapp.ui.list.all

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.patrykkosieradzki.theanimalapp.R
import com.patrykkosieradzki.theanimalapp.databinding.AllAnimalsFragmentBinding
import com.patrykkosieradzki.theanimalapp.utils.BaseFragment
import com.patrykkosieradzki.theanimalapp.utils.NavigationResult
import com.patrykkosieradzki.theanimalapp.utils.extensions.navigateTo
import com.patrykkosieradzki.theanimalapp.utils.extensions.removeItemDecorations
import com.patrykkosieradzki.theanimalapp.utils.extensions.valueNN
import kotlinx.coroutines.launch
import timber.log.Timber

class AllAnimalsFragment :
    BaseFragment<AllAnimalsViewState, AllAnimalsViewModel, AllAnimalsFragmentBinding>(
        R.layout.all_animals_fragment, AllAnimalsViewModel::class
    ),
    NavigationResult {

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
            updateAnimalsEvent.observe(viewLifecycleOwner) {
                lifecycleScope.launch {
                    adapter.submitData(it)
                }
            }
            showAnimalDetailsEvent.observe(viewLifecycleOwner) { position ->
                val directions = AllAnimalsFragmentDirections.toAnimalDetailsFragment(position)
                navigateTo(directions)
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

    override fun onNavigationResult(result: Bundle) {
        Timber.d("Back navigation args received ${result["position"]}")
        with(binding.animalsRecyclerView) {
            post {
                layoutManager?.scrollToPosition(result["position"] as Int)
            }
        }
    }
}
